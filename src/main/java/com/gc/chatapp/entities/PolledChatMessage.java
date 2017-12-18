package com.gc.chatapp.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "polled_chat_messages")
public class PolledChatMessage extends ChatMessage {

	@Temporal(TemporalType.DATE)
	@Column(name = "expiration_date")
	private Date expirationDate;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "polled_message_users", 
		joinColumns = { @JoinColumn(name = "chat_message_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private List<User> receivers;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_message_id")
	@Column(name = "polled_responses")
	private List<PolledResponse> polledResponses;

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public List<PolledResponse> getPolledResponses() {
		return polledResponses;
	}

	public void setPolledResponses(List<PolledResponse> polledResponses) {
		this.polledResponses = polledResponses;
	}

	public List<User> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<User> receivers) {
		this.receivers = receivers;
	}

	@Override
	public String toString() {
		return "PolledChatMessage [expirationDate=" + expirationDate + ", receivers=" + receivers + ", polledResponses="
				+ polledResponses + "]";
	}

	
}
