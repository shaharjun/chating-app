package com.gc.chatapp.service;

import java.util.List;

import com.gc.chatapp.entities.User;

public interface UserService {

	// Register user
	// Arjun
	User createUser(User user);

	// Search user
	// Arjun
	User getUserByEmail(String emailId);

	// Search user
	// Rachna
	List<User> getUsersByName(String name);

	// Login User
	// Arjun
	User login(String mailId, String password);

	// User can update profile details
	// Rohan
	boolean updateProfile(User user);

	// user can change password
	// Rohan
	boolean changePassword(User user);

	public List<User> getAllUsers();

	// Add new user to user contacts
	// Rachna
	boolean addUserToUserContacts(User userToBeAdded, User currentUser);
}
