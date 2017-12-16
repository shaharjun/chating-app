package com.gc.chatapp.dao.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gc.chatapp.dao.ChatDao;
import com.gc.chatapp.entities.ChatGroup;
import com.gc.chatapp.entities.ChatMessage;
import com.gc.chatapp.entities.ChatReminder;
import com.gc.chatapp.entities.GroupChatMessage;
import com.gc.chatapp.entities.PolledChatMessage;
import com.gc.chatapp.entities.PolledResponse;
import com.gc.chatapp.entities.User;

//@Repository
public class ChatDaoImpl implements ChatDao{

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public ChatGroup createChatGroup(ChatGroup chatGroup, User creator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUserToChatGroup(User user, ChatGroup chatGroup) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addGroupAdminToChatGroup(User groupAdmin, ChatGroup chatGroup, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeGroupMemberFromChatGroup(User groupAdmin, ChatGroup chatGroup, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ChatGroup> getAllChatGroupsOfAUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sendGroupChatMessage(ChatGroup chatGroup, GroupChatMessage groupChatMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ChatMessage> getGroupChatMessages(User user, ChatGroup chatGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exitChatGroup(ChatGroup chatGroup, User user) {
		// TODO Auto-generated method stub
		return false;
	}

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
	public boolean deleteChatGroup(ChatGroup chatGroup) {
		// TODO Auto-generated method stub
		return false;
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
