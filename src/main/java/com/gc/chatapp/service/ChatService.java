package com.gc.chatapp.service;

import java.util.List;

import com.gc.chatapp.entities.ChatGroup;
import com.gc.chatapp.entities.ChatMessage;
import com.gc.chatapp.entities.ChatReminder;
import com.gc.chatapp.entities.GroupChatMessage;
import com.gc.chatapp.entities.PolledChatMessage;
import com.gc.chatapp.entities.PolledResponse;
import com.gc.chatapp.entities.User;

public interface ChatService {
	
	//Create Chat group as a Group Creator
	// Rajat
	 ChatGroup createChatGroup(ChatGroup chatGroup, User creator);

	//Admin can add users to chat group
	// Rajat
	 boolean addUserToChatGroup(User user, ChatGroup chatGroup);

	//Creator can make participant of the group admin
	// Rajat
	 boolean addGroupAdminToChatGroup(User groupAdmin, ChatGroup chatGroup, User user);

	//Group Admin can remove a member from the chat group 
	// Rajat
	 boolean removeGroupMemberFromChatGroup(User groupAdmin, ChatGroup chatGroup, User user);

	// Get all chat groups
	// Rachna
	 List<ChatGroup> getAllChatGroupsOfAUser(User user);

	//Send group chat message
	// Utkarsh
	 boolean sendGroupChatMessage(ChatGroup chatGroup, GroupChatMessage groupChatMessage);

	//To get messages of a particular group
	// Utkarsh
	 List<ChatMessage> getGroupChatMessages(User user, ChatGroup chatGroup);
	//Exit from chat group
	 boolean exitChatGroup(ChatGroup chatGroup, User user);

	// Add Polled chat message
	// Anees
	 boolean addPolledChatMessage (PolledChatMessage polledChatMessage);

	//add response of each user
	 boolean addPolledResponse(PolledResponse polledResponse, PolledChatMessage polledChatMessage);

	// Get all polled chat message responses
	// Anees
	 List<PolledResponse> getAllPolledChatMessages(User user, PolledChatMessage polledChatMessage);

	//Delete chat group
	// Rajat
	 boolean deleteChatGroup(ChatGroup chatGroup);
	 
	//To fetch all star marked messages to be displayed
	// Riya
	 List<ChatMessage> getAllChatMessages(long userId, boolean isStarred);

	// Set Chat Message as Starred
	// Riya
	 boolean setStarredChatMessage(ChatMessage chatMessage);

	// Get all contacts 
	// Utkarsh
	 List<User> getAllUserContacts(User user);

	// Send a message to an User
	// Utkarsh
	 boolean sendChatMessage(ChatMessage chatMessage);

	//To get messages of a particular user
	// Utkarsh
	 List<ChatMessage> getIndividualChatMessages(User user, User contact);

	//Store the reminder message for particular schedule date
	// Deeksha
	 boolean addChatReminder(ChatReminder chatReminder);

	// Add new user to user contacts
	// Rachna
	 boolean addUserToUserContacts(User userToBeAdded, User currentUser);



}
