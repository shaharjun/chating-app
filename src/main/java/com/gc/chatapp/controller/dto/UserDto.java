package com.gc.chatapp.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.gc.chatapp.entities.User;

public class UserDto {
	
	private long userId;
	private String fullName;
	private String password;
	private String phoneNo;
	private String emailId;
	private String profilePictureUrl;
	private String sessionId;
	private String globalErrorMessage;
	private String globalSuccessMessage;
	// to indicate login status, can be removed later
	private boolean userStatus = false;
	private List<User> userList = new ArrayList<>();
	
	private String requestCreatorEmail; 
	
	public String getRequestCreatorEmail() {
		return requestCreatorEmail;
	}

	public void setRequestCreatorEmail(String requestCreatorEmail) {
		this.requestCreatorEmail = requestCreatorEmail;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public boolean isUserStatus() {
		return userStatus;
	}

	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}

	private List<User>chatContacts = new ArrayList<>();

	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	

	public String getGlobalErrorMessage() {
		return globalErrorMessage;
	}

	public void setGlobalErrorMessage(String globalErrorMessage) {
		this.globalErrorMessage = globalErrorMessage;
	}

	public String getGlobalSuccessMessage() {
		return globalSuccessMessage;
	}

	public void setGlobalSuccessMessage(String globalSuccessMessage) {
		this.globalSuccessMessage = globalSuccessMessage;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", fullName=" + fullName + ", password=" + password + ", phoneNo="
				+ phoneNo + ", emailId=" + emailId + ", profilePictureUrl=" + profilePictureUrl + ", sessionId="
				+ sessionId + ", userStatus=" + userStatus + ", chatContacts=" + chatContacts + "]";
	}

	

}
