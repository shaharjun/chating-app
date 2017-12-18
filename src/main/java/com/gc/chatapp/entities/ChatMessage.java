package com.gc.chatapp.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "chat_messages")
public abstract class ChatMessage {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "chat_message_id")
	protected long chatMessageId;
	
	@Column(name = "chat_message_text")
	protected String chatMessageText;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_on")
	protected Date createdOn;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "chat_status")
	protected ChatStatus chatStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "chat_type")
	protected ChatType chatType;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sender_id")
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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "ChatMessage [chatMessageId=" + chatMessageId + ", chatMessageText=" + chatMessageText + ", createdOn="
				+ createdOn + ", chatStatus=" + chatStatus + ", chatType=" + chatType + ", creator=" + creator.getEmailId() + "]";
	}
	
	
}
