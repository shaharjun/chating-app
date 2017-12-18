package com.gc.chatapp.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long userId;

	@Column(name = "full_name")
	private String fullName;

	private String password;

	@Column(name = "phone_no")
	private long phoneNo;

	@Column(name = "email_id", unique = true)
	private String emailId;

	@Column(name = "profile_picture_url")
	private String profilePictureURL;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	private Set<RequestInfo> requestInfo;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "user_contacts", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "contact_user_id") })
	private Set<User> contacts;

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

	public Set<User> getContacts() {
		return contacts;
	}

	public void setContacts(Set<User> contacts) {
		this.contacts = contacts;
	}

	public Set<RequestInfo> getRequestInfo() {
		return requestInfo;
	}

	public void setRequestInfo(Set<RequestInfo> requestInfo) {
		this.requestInfo = requestInfo;
	}

	@Override
	public String toString() {
		return "User : [userId=" + userId + ", fullName=" + fullName + ", password=" + password + ", phoneNo=" + phoneNo
				+ ", emailId=" + emailId + ", profilePictureURL=" + profilePictureURL + ", requestInfo=" + requestInfo
				+ ", \ncontacts=" + getContactsToString() + "]";
	}

	private String getContactsToString() {
		String str = "";
		if (contacts != null) {
			for (User user : contacts) {
				str += user.getEmailId() + " ";
			}
		}
		return str;
	}
}
