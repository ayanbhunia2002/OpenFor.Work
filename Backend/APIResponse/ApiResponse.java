package com.OpenForWork.APIResponse;
public class ApiResponse {
	
	String name;
	String bio;
	int public_repos;
	int followers;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public void setPublic_repos(int public_repos) {
		this.public_repos = public_repos;
	}
	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public String getName() {
		return name;
	}
	public String getBio() {
		return bio;
	}
	public int getPublic_repos() {
		return public_repos;
	}
	public int getFollowers() {
		return followers;
	}
}

