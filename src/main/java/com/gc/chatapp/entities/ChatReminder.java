package com.gc.chatapp.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "chat_reminders")
public class ChatReminder extends IndividualChatMessage {
	
	@Temporal(TemporalType.DATE)
	@Column(name = "scheduled_date")
	private Date scheduledDate;

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	@Override
	public String toString() {
		return "ChatReminder [scheduledDate=" + scheduledDate + "]";
	}
	
}
