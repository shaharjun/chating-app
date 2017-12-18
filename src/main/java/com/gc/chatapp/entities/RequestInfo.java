package com.gc.chatapp.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "request_info")
public class RequestInfo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "request_info_id")
	private long requestInfoId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "request_sent_on")
	private Date requestSentOn;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "response_date")
	private Date responseDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "request_status")
	private RequestStatus requestStatus;

	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getRequestSentOn() {
		return requestSentOn;
	}

	public void setRequestSentOn(Date requestSentOn) {
		this.requestSentOn = requestSentOn;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public long getRequestInfoId() {
		return requestInfoId;
	}

	public void setRequestInfoId(long requestInfoId) {
		this.requestInfoId = requestInfoId;
	}

	@Override
	public String toString() {
		return "RequestInfo : [requestInfoId=" + requestInfoId + ", user=" + user.getEmailId() + ", requestSentOn=" + requestSentOn
				+ ", responseDate=" + responseDate + ", requestStatus=" + requestStatus + "]";
	}
	
	
}
