package com.gc.chatapp.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gc.chatapp.controller.dto.MessageDto;
import com.gc.chatapp.entities.ChatMessage;
import com.gc.chatapp.service.ChatService;
import com.google.gson.Gson;

@Controller
public class ChatController {
	
	private static final Logger logger =
			Logger.getLogger(ChatController.class.getName());
	
	@Autowired
	private ChatService chatService;
	
	@RequestMapping(value="/sendChat", method=RequestMethod.POST,
			produces="application/json", consumes="application/json")
	public @ResponseBody String sendChat(@RequestBody ChatMessage chatMessage)
	{

		String response;

		MessageDto messageDto = new MessageDto();;

		JSONObject jsonObject = new JSONObject();

		logger.log(Level.INFO, "Chat message data is: " + chatMessage.toString()); 

		boolean sendChatMessageSuccess = chatService.sendChatMessage(chatMessage);

		if(sendChatMessageSuccess) {			
			messageDto.setMessageSent(true);
		}
		else {
			messageDto.setMessageSent(false);
		}

		Gson gson = new Gson();

		return gson.toJson(messageDto);
	}

}
