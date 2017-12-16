package com.gc.chatapp.controller.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gc.chatapp.entities.ChatType;

public class PolledMessageDto {
	
	private long polledChatMessageId;
	private long userId;
	private String polledChatMessageText;
	private boolean starred = false;
	private ChatType chatType;
	private List<PollReciever> users;
	private Date expirationDate;
	private String polledResponseType;
	private int pollYesCount;
	private int pollNoCount;
	private int pollCantSayCount;

}
