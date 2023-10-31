package com.OpenForWork.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.OpenForWork.Entity.*;
import com.OpenForWork.APIResponse.ApiResponse;
import com.OpenForWork.DAO.UserRepository;



@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "OpenFor.Work");
		return "home";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register | Edged & Taken");
		model.addAttribute("user", new User());
		model.addAttribute("profileNames", Arrays.asList("GitHub", "Behance", "Stack Overflow", "LeetCode", "Dribbble", "Figma", "Medium", "Personal Blog", "Facebook", "Instagram", "YouTube"));
		return "signup";
	}
	
//	Save details in DB
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result1,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session) {
		try {
			if (!agreement) {
				System.out.println("You haven't agreed T&C");
				throw new Exception("You haven't agreed T&C");
			}
			if (result1.hasErrors()) {
				System.out.println("ERROR " + result1.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			
			// Access and process the dynamic profile links
			System.out.println("Profile Links");
	        Map<String, String> profileLinks = user.getProfileLinks();
	        System.out.println(profileLinks);
	        for (String key: profileLinks.keySet())
	        {
	        	System.out.println(key+" "+profileLinks.get(key));
	        }
			
			user.setEnabled(true);
			user.setImage("default.png");
			user.setPassword(user.getPassword());

			this.userRepository.save(user);

			model.addAttribute("user", new User());

			System.out.println(user);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			return "signup";
		}
		return "redirect:signup";
	}
	
	@GetMapping("/signin")
	public String customLogIn(Model model) {
		model.addAttribute("title", "Log In");
		return "login";
	}
	
	@GetMapping("/find/user/{username}")
	public String findUser(@PathVariable("username") String username,Model model) {
		System.out.println(username);
		User user = userRepository.findByEmail(username);
		model.addAttribute("profile",user);
		String skills = user.getSkills(); 
		List<String> skillList = Arrays.asList(skills.split(","));
		model.addAttribute("skillList", skillList);

        if (user != null) {
            Map<String, String> profileLinks = user.getProfileLinks();

            String gitHubLink = profileLinks.get("GitHub");

            if (gitHubLink != null) {
     
                model.addAttribute("gitHubLink", gitHubLink);
                
                String [] a = gitHubLink.split("/");
                String github_username = a[a.length-1];
                System.out.println("username is "+github_username);
                
                String apiurl = "https://api.github.com/users/"+github_username;
                GithubUser githubuser = fetchData(apiurl);
        		model.addAttribute("user",githubuser);
        		model.addAttribute("username",github_username);
            } 
            else 
            {
            	System.out.println("Github link is not avaiable");
            }
        } else {
            System.out.println("User not found");
        }

		return "userHome";
	}
	
	private GithubUser fetchData(String apiurl) {
		RestTemplate resttemplate = new RestTemplate();
		ApiResponse response =resttemplate.getForObject(apiurl, ApiResponse.class);
		if (response != null)
		{
			GithubUser user = new GithubUser();
			user.setName(response.getName());
			user.setBio(response.getBio());
			user.setFollowers(response.getFollowers());
			user.setPublic_repos(response.getPublic_repos());
			System.out.println(user);
			return user;
		}
		return null;
	}
}
