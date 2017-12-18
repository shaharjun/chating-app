package com.gc.chatapp.dao.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gc.chatapp.dao.UserDao;
import com.gc.chatapp.entities.RequestInfo;
import com.gc.chatapp.entities.ChatReminder;
import com.gc.chatapp.entities.ChatStatus;
import com.gc.chatapp.entities.ChatType;
import com.gc.chatapp.entities.IndividualChatMessage;
import com.gc.chatapp.entities.PolledChatMessage;
import com.gc.chatapp.entities.PolledResponse;
import com.gc.chatapp.entities.RequestStatus;
import com.gc.chatapp.entities.ResponseText;
import com.gc.chatapp.entities.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public User createUser(User user) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(user);
		tx.commit();
		entityManager.close();

		return user;
	}

	@Override
	public User getUserByEmail(String emailId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		String hql = "from User u where u.emailId=:email";
		Query query = entityManager.createQuery(hql, User.class);
		query.setParameter("email", emailId);
		User user = (User) query.getSingleResult();
		entityManager.close();
		return user;
	}

	@Override
	public List<User> getUsersByName(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		String hql = "from User u where u.fullName=:name";
		Query query = entityManager.createQuery(hql, User.class);
		query.setParameter("name", name);
		List<User> list = (List<User>) query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	public User login(String emailId, String password) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		String hql = "from User u where u.emailId=:email and u.password=:password";
		Query query = entityManager.createQuery(hql, User.class);
		query.setParameter("email", emailId);
		query.setParameter("password", password);
		User user = (User) query.getSingleResult();
		entityManager.close();
		return user;
	}

	@Override
	public boolean updateProfile(User user) {

		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			User sessionUser = entityManager.find(User.class, user.getUserId());

			sessionUser.setPhoneNo(user.getPhoneNo());
			sessionUser.setFullName(user.getFullName());
			sessionUser.setProfilePictureURL(user.getProfilePictureURL());
			tx.commit();
			entityManager.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean changePassword(User user) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			User sessionUser = entityManager.find(User.class, user.getUserId());
			sessionUser.setPassword(user.getPassword());
			tx.commit();
			entityManager.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public List<User> getAllUsers() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		String hql = "from User";
		Query query = entityManager.createQuery(hql, User.class);
		List<User> list = (List<User>) query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	public boolean addUserToUserContacts(User userToBeAdded, User currentUser) {
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			User sessionUser = entityManager.find(User.class, currentUser.getUserId());
			sessionUser.getContacts().add(userToBeAdded);
			tx.commit();
			entityManager.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
