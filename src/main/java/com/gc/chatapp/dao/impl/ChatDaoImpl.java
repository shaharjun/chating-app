package com.gc.chatapp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gc.chatapp.dao.ChatDao;
import com.gc.chatapp.entities.ChatMessage;
import com.gc.chatapp.entities.ChatReminder;
import com.gc.chatapp.entities.PolledChatMessage;
import com.gc.chatapp.entities.PolledResponse;
import com.gc.chatapp.entities.User;

@Repository
public class ChatDaoImpl implements ChatDao{

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
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
	
	@Override
	public List<User> getAllUserContacts(User user) {
		
		
		return null;
	}
	
	@Override
	public PolledChatMessage addPolledChatMessage(PolledChatMessage polledChatMessage) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(polledChatMessage);
		tx.commit();
		entityManager.close();

		return polledChatMessage;

	}

	@Override
	public PolledResponse addPolledResponse(PolledResponse polledResponse, PolledChatMessage polledChatMessage) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(polledResponse);
		tx.commit();
		entityManager.close();

		return polledResponse;

	}

	@Override
	public List<PolledChatMessage> getAllPolledChatMessages(String email) {
		List<PolledChatMessage> polledChatMessages = null;
		
		return polledChatMessages;
	}

	@Override
	public List<ChatMessage> getAllChatMessages(long userId, boolean isStarred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setStarredChatMessage(ChatMessage chatMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendChatMessage(ChatMessage chatMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ChatMessage> getIndividualChatMessages(User user, User contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addChatReminder(ChatReminder chatReminder) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public PolledChatMessage getPolledChatMessageById(long id) {
		PolledChatMessage polledChatMessage = null;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		polledChatMessage = entityManager.find(PolledChatMessage.class, id);
		entityManager.close();
		
		return polledChatMessage;
	}

}
