package com.OpenForWork.Entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.*;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Name is required!")
    @Size(min = 2, max = 35, message = "min 2 and max 35 chars are required!")
    private String name;

    @Column(unique = true) // email is unique
    private String email;
    private String password;
    private String image;
    private String bio;
    private boolean enabled;
    private String skills;
    private String location;

    // Profile links
    @ElementCollection
    @CollectionTable(name = "USER_PROFILE_LINKS", joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyColumn(name = "profile_type")
    @Column(name = "profile_link")
    private Map<String, String> profileLinks = new HashMap<>();

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProfileLinks(Map<String, String> profileLinks) {
        this.profileLinks = profileLinks;
    }

    public Map<String, String> getProfileLinks() {
        return profileLinks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }

    public String getBio() {
        return bio;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getSkills() {
        return skills;
    }

    public String getLocation() {
        return location;
    }

    public User() {
        super();
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", image=" + image
                + ", bio=" + bio + ", enabled=" + enabled + "location " + location + " ]";
    }
}
