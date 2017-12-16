package com.gc.chatapp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gc.chatapp.dao.UserDao;
import com.gc.chatapp.entities.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public long createUser(User user) {
		
		// polluted code
		long statusCode = 0;
		
		if(user.getEmailId().equals("arjunshah248@gmail.com")) {
			statusCode = 0;
		}
		else {
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
