package com.gc.chatapp.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.chatapp.controller.UserController;
import com.gc.chatapp.dao.UserDao;
import com.gc.chatapp.dao.impl.UserDaoImpl;
import com.gc.chatapp.entities.User;
import com.gc.chatapp.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger logger =
			Logger.getLogger(UserServiceImpl.class.getName());

	@Autowired
	private UserDao userDao;
	@Override
	public long createUser(User user) {
		logger.log(Level.INFO, "Called createUser in Service with data: " + user);
		return userDao.createUser(user);
	}

	@Override
	public List<User> getUserByEmail(String emailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(String mailId, String password) {
		// TODO Auto-generated method stub
		logger.log(Level.INFO, "Called login in Service with data: " + mailId);
		return userDao.login(mailId, password);
	}

	@Override
	public boolean updateProfile(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProfilePicture(String base64) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePassword(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
