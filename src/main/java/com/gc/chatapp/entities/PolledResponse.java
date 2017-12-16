package com.gc.chatapp.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PolledResponse {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long polledResponseId;
	
	@Temporal(TemporalType.DATE)
	private Date responseDate;
	
	@Enumerated(EnumType.STRING)
	private ResponseText responseText;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;

	public long getPolledResponseId() {
		return polledResponseId;
	}

	public void setPolledResponseId(long polledResponseId) {
		this.polledResponseId = polledResponseId;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public ResponseText getResponseText() {
		return responseText;
	}

	public void setResponseText(ResponseText responseText) {
		this.responseText = responseText;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
