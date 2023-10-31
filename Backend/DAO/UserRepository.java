package com.OpenForWork.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;


import com.OpenForWork.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
//	@Query("select u from User u where u.email = :email")
//	public User getUserByUserName(@Param("email") String email);
	User findByEmail(String email);
	@Query("select u.profileLinks from User u where u.email = :email")
    Map<String, String> getAllProfileLinksByUsername(@Param("email") String username);
}
