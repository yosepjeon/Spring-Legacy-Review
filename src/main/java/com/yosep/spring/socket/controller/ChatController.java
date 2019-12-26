package com.yosep.spring.socket.controller;

import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.yosep.spring.socket.model.dto.ChatMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ChatController {
	private final SimpMessageSendingOperations messagingTemplate;
	
	@MessageMapping(value="/chat/message")
	public void message(ChatMessage message) {
		messagingTemplate.convertAndSend("/sub/char/id/" + message.getChatId(), message);
	}
}
