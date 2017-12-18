package com.gc.chatapp.dao;

import java.util.List;

import com.gc.chatapp.entities.ChatMessage;
import com.gc.chatapp.entities.ChatReminder;
import com.gc.chatapp.entities.PolledChatMessage;
import com.gc.chatapp.entities.PolledResponse;
import com.gc.chatapp.entities.User;

public interface ChatDao {
	// Add new user to user contacts
	// Rachna
	public boolean addUserToUserContacts(User userToBeAdded, User currentUser);

	// Get all contacts
	// Utkarsh
	public List<User> getAllUserContacts(User user);
	
	// Set Chat Message as Starred
	// Riya
	public boolean setStarredChatMessage(ChatMessage chatMessage);

	// To fetch all star marked messages to be displayed
	// Riya
	public List<ChatMessage> getAllChatMessages(long userId, boolean isStarred);

	// Send a message to an User
	// Utkarsh
	public boolean sendChatMessage(ChatMessage chatMessage);

	// To get messages of a particular user
	// Utkarsh
	public List<ChatMessage> getIndividualChatMessages(User user, User contact);

	// Store the reminder message for particular schedule date
	// Deeksha
	public boolean addChatReminder(ChatReminder chatReminder);

	// Add Polled chat message
	// Anees
	public PolledChatMessage addPolledChatMessage(PolledChatMessage polledChatMessage);

	// Get Polled chat message by id
	// Anees
	public PolledChatMessage getPolledChatMessageById(long id);

	// add response of each user
	public PolledResponse addPolledResponse(PolledResponse polledResponse, PolledChatMessage polledChatMessage);

	// Get all polled chat message responses
	// Anees
	public List<PolledChatMessage> getAllPolledChatMessages(String email);

}
