package com.myApp.service;

import java.util.Set;

import com.myApp.domain.User;
import com.myApp.domain.Security.UserRole;

public interface UserService {

	User findByUsername(String username);
	User findByEmail(String email);
	
	boolean checkUserExists(String username,String email);
	boolean checkUsernameExists(String username);
	boolean checkEmailExists(String email);
	void save(User user);
	
	User createUser(User user,Set<UserRole>userRoles);
	
	User saveUser (User user);
}
