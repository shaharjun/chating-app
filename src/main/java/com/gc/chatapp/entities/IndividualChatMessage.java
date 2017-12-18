package com.gc.chatapp.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "individual_chat_messages")
public class IndividualChatMessage extends ChatMessage{

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	protected User receiver;
	
	private boolean starred;

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public boolean isStarred() {
		return starred;
	}

	public void setStarred(boolean starred) {
		this.starred = starred;
	}

	@Override
	public String toString() {
		return "IndividualChatMessage [receiver=" + receiver + ", starred=" + starred + "]";
	}

	
}
