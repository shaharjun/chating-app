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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ChatGroup {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long chatGroupId;
	
	private String chatGroupName;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private User creator;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="chartgroup_admins",joinColumns={@JoinColumn(name="chatGroupId")},inverseJoinColumns={@JoinColumn(name="userId")})
	private Set<User> groupAdmins;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="chartgroup_member",joinColumns={@JoinColumn(name="chatGroupId")},inverseJoinColumns={@JoinColumn(name="userId")})
	private Set<User> groupMembers;

	public long getChatGroupId() {
		return chatGroupId;
	}

	public void setChatGroupId(long chatGroupId) {
		this.chatGroupId = chatGroupId;
	}

	public String getChatGroupName() {
		return chatGroupName;
	}

	public void setChatGroupName(String chatGroupName) {
		this.chatGroupName = chatGroupName;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Set<User> getGroupAdmins() {
		return groupAdmins;
	}

	public void setGroupAdmins(Set<User> groupAdmins) {
		this.groupAdmins = groupAdmins;
	}

	public Set<User> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(Set<User> groupMembers) {
		this.groupMembers = groupMembers;
	}
	
}
