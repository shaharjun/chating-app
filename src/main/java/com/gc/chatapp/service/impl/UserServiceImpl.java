package com.gc.chatapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.chatapp.dao.UserDao;
import com.gc.chatapp.dao.impl.UserDaoImpl;
import com.gc.chatapp.entities.User;
import com.gc.chatapp.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	@Override
	public long createUser(User user) {
		System.out.println("Called createUser in Service with data: " + user);
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
		return null;
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
