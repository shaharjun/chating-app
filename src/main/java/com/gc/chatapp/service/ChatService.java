package com.gc.chatapp.service;

import java.util.List;

import com.gc.chatapp.entities.ChatMessage;
import com.gc.chatapp.entities.ChatReminder;
import com.gc.chatapp.entities.PolledChatMessage;
import com.gc.chatapp.entities.PolledResponse;
import com.gc.chatapp.entities.User;

public interface ChatService {
	
	//Send group chat message
	// Utkarsh
//	 boolean sendGroupChatMessage(ChatGroup chatGroup, GroupChatMessage groupChatMessage);

	// Add Polled chat message
	// Anees
	 boolean addPolledChatMessage (PolledChatMessage polledChatMessage);

	//add response of each user
	 boolean addPolledResponse(PolledResponse polledResponse, PolledChatMessage polledChatMessage);

	// Get all polled chat message responses
	// Anees
	 List<PolledResponse> getAllPolledChatMessages(User user, PolledChatMessage polledChatMessage);

	//To fetch all star marked messages to be displayed
	// Riya
	 List<ChatMessage> getAllStarredChatMessages(long userId, boolean isStarred);

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