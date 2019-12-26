package com.yosep.spring.socket.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/chat")
public class ChatRoomController {
	@RequestMapping(value="/checkChatExist")
	public Map<String,Object> checkIsChatExist(String chatId) {
		
		
		return null;
	}
	
	@RequestMapping(value="/create/id")
	public Map<String,Object> createChat(String chatId) {
		
		return null;
	}
	
	@RequestMapping(value="/id")
	public Map<String, Object> enterChat(String chatId) {
		
		return null;
	}
}
