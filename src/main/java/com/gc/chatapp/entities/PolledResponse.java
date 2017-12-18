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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "polled_responses")
public class PolledResponse {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "polled_response_id")
	private long polledResponseId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "response_date")
	private Date responseDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "response_text")
	private ResponseText responseText;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
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

	@Override
	public String toString() {
		return "PolledResponse [polledResponseId=" + polledResponseId + ", responseDate=" + responseDate
				+ ", responseText=" + responseText + ", user=" + user + "]";
	}
	
}
