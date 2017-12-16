package com.gc.chatapp.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ChatReminder extends IndividualChatMessage{
	
	@Temporal(TemporalType.DATE)
	private Date scheduledDate;

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	
	
}
