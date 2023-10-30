package com.OpenForWork.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.OpenForWork.Entity.*;
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

}
