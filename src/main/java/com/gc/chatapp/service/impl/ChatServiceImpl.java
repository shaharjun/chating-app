package com.gc.chatapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.chatapp.dao.ChatDao;
import com.gc.chatapp.entities.ChatMessage;
import com.gc.chatapp.entities.ChatReminder;
import com.gc.chatapp.entities.PolledChatMessage;
import com.gc.chatapp.entities.PolledResponse;
import com.gc.chatapp.entities.User;
import com.gc.chatapp.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService{
	@Autowired
	private ChatDao chatDao;
	
	@Override
	public boolean addPolledChatMessage(PolledChatMessage polledChatMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPolledResponse(PolledResponse polledResponse, PolledChatMessage polledChatMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PolledResponse> getAllPolledChatMessages(User user, PolledChatMessage polledChatMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatMessage> getAllStarredChatMessages(long userId, boolean isStarred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setStarredChatMessage(ChatMessage chatMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getAllUserContacts(User user) {
		// TODO Auto-generated method stub
		return null;
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
	public boolean addUserToUserContacts(User userToBeAdded, User currentUser) {
		// TODO Auto-generated method stub
		return false;
	}

}
