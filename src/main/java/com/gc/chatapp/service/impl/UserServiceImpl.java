package com.gc.chatapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.chatapp.dao.impl.UserDaoImpl;
import com.gc.chatapp.entities.User;
import com.gc.chatapp.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDaoImpl userDao;
	
	@Override
	public User createUser(User user) {
		return userDao.createUser(user);
	}

	@Override
	public User getUserByEmail(String emailId) {
		return userDao.getUserByEmail(emailId);
	}

	@Override
	public List<User> getUsersByName(String name) {
		return userDao.getUsersByName(name);
	}

	@Override
	public User login(String mailId, String password) {
		return userDao.login(mailId, password);
	}

	@Override
	public boolean updateProfile(User user) {
		return userDao.updateProfile(user);
	}

	@Override
	public boolean changePassword(User user) {
		return userDao.changePassword(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public boolean addUserToUserContacts(User userToBeAdded, User currentUser) {
		// TODO Auto-generated method stub
		return userDao.addUserToUserContacts(userToBeAdded, currentUser);
	}
}
