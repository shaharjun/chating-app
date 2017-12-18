package com.gc.chatapp.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ChatMessage {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	protected long chatMessageId;
	
	protected String chatMessageText;
	
	@Temporal(TemporalType.DATE)
	protected Date createdOn;
	
	@Enumerated(EnumType.STRING)
	protected ChatStatus chatStatus;
	
	@Enumerated(EnumType.STRING)
	protected ChatType chatType;
	
	@Type(type="true_false")
	protected boolean starred;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	protected User creator;

	public long getChatMessageId() {
		return chatMessageId;
	}

	public void setChatMessageId(long chatMessageId) {
		this.chatMessageId = chatMessageId;
	}

	public String getChatMessageText() {
		return chatMessageText;
	}

	public void setChatMessageText(String chatMessageText) {
		this.chatMessageText = chatMessageText;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public ChatStatus getChatStatus() {
		return chatStatus;
	}

	public void setChatStatus(ChatStatus chatStatus) {
		this.chatStatus = chatStatus;
	}

	public ChatType getChatType() {
		return chatType;
	}

	public void setChatType(ChatType chatType) {
		this.chatType = chatType;
	}

	public boolean isStarred() {
		return starred;
	}

	public void setStarred(boolean starred) {
		this.starred = starred;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "ChatMessage [chatMessageId=" + chatMessageId + ", chatMessageText=" + chatMessageText + ", createdOn="
				+ createdOn + ", chatStatus=" + chatStatus + ", chatType=" + chatType + ", starred=" + starred
				+ ", creator=" + creator + "]";
	}
	
}
