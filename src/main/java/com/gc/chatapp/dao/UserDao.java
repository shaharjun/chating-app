package com.gc.chatapp.dao;

import java.util.List;

import com.gc.chatapp.entities.User;

public interface UserDao {
	
	// Register user
		// Arjun
		long createUser(User user);

		// Search user
		// Arjun
		List<User> getUserByEmail(String emailId);

		// Search user
		// Rachna
		List<User> getUserByName(String name);

		// Login User
		// Arjun
		User login(String mailId, String password);

		// User can update profile details
		// Rohan
		boolean updateProfile(User user);

		// User can update profile picture
		// Rohan
		boolean updateProfilePicture(String base64);

		// user can change password
		// Rohan
		boolean changePassword(User user);


}
