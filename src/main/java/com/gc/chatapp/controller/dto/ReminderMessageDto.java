package com.gc.chatapp.controller.dto;

import java.util.Date;

import com.gc.chatapp.entities.ChatStatus;
import com.gc.chatapp.entities.ChatType;

public class ReminderMessageDto {
	
	private String creator;
	private String receiver;
	private long chatMessageId;
	private Date createdOn;
	private boolean starred = false;
	private String chatMessageText;
	private int messageType;
	private ChatStatus chatStatus;
	private ChatType chatType;
	private Date scheduledDate;
}
