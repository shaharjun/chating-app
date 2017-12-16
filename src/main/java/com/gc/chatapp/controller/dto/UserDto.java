package com.gc.chatapp.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.gc.chatapp.entities.User;

public class UserDto {
	
	private int userId;
	private String fullName;
	private String password;
	private String phoneNo;
	private String emailId;
	private String profilePictureUrl;
	
	private List<User>chatContacts = new ArrayList<>();

	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

	public List<User> getChatContacts() {
		return chatContacts;
	}

	public void setChatContacts(List<User> chatContacts) {
		this.chatContacts = chatContacts;
	}

}
