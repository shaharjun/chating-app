package com.gc.chatapp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class GroupChatMessage extends ChatMessage{
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="groupchatmessage_user",joinColumns={@JoinColumn(name="chatMessageId")},inverseJoinColumns={@JoinColumn(name="userId")})
	private List<User> receivers;

	public List<User> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<User> receivers) {
		this.receivers = receivers;
	}

	
}
