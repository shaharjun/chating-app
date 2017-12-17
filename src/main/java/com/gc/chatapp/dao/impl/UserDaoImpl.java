package com.gc.chatapp.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gc.chatapp.controller.UserController;
import com.gc.chatapp.dao.UserDao;
import com.gc.chatapp.entities.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	private static final Logger logger =
			Logger.getLogger(UserDaoImpl.class.getName());

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public long createUser(User user) {
		
		// polluted code
		long statusCode = 0;
		
		if(user.getEmailId().equals(user.getEmailId())) {
			statusCode = 1;
		}
		
		System.out.println("Status code is " + statusCode);
		return statusCode;
		// polluted code ends
		
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
		// polluted code
		boolean areCredentialsCorrect = false;
		User user = null;
		if(mailId.equals(mailId)) {
			areCredentialsCorrect = true;
		}
		if(areCredentialsCorrect) {
			user = new User();
			user.setUserId(1);
			user.setEmailId(mailId);
			user.setPhoneNo(9718554554L);
			user.setProfilePictureURL("resources/profilepicture/default.txt");
			// user object will have many more fields(like chat contacts list) 
		}
		// polluted code ends
		return user;
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
