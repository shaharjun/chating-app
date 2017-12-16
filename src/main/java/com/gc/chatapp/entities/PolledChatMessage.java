package com.gc.chatapp.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PolledChatMessage extends GroupChatMessage{
	
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="chatMessageId")
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
		
}
