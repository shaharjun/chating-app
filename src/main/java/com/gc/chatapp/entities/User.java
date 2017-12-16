package com.gc.chatapp.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long userId;
	private String fullName;
	private String password;
	private long phoneNo;
	private String emailId;
	private String profilePictureURL;

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="user_contacts",joinColumns={@JoinColumn(name="userId")},inverseJoinColumns={@JoinColumn(name="contactUserId")})
	private Set<User> chatContacts;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="chatContacts")
	private Set<User> contacts;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="chartgroup_member",joinColumns={@JoinColumn(name="userId")},inverseJoinColumns={@JoinColumn(name="chatGroupId")})
	private Set<ChatGroup> chatGroups;

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

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getProfilePictureURL() {
		return profilePictureURL;
	}

	public void setProfilePictureURL(String profilePictureURL) {
		this.profilePictureURL = profilePictureURL;
	}

	public Set<User> getChatContacts() {
		return chatContacts;
	}

	public void setChatContacts(Set<User> chatContacts) {
		this.chatContacts = chatContacts;
	}

	public Set<User> getContacts() {
		return contacts;
	}

	public void setContacts(Set<User> contacts) {
		this.contacts = contacts;
	}

	public Set<ChatGroup> getChatGroups() {
		return chatGroups;
	}

	public void setChatGroups(Set<ChatGroup> chatGroups) {
		this.chatGroups = chatGroups;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullName + ", password=" + password + ", phoneNo=" + phoneNo
				+ ", emailId=" + emailId + ", profilePictureURL=" + profilePictureURL + ", chatContacts=" + chatContacts
				+ ", contacts=" + contacts + ", chatGroups=" + chatGroups + "]";
	}
	
	
}
