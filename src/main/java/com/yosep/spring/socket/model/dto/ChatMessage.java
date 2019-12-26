package com.yosep.spring.socket.model.dto;

import lombok.Data;

@Data
public class ChatMessage {
	public enum MessageType {
		TALK
	}
	
	private MessageType type;
	private String chatId;
	private String sender;
	private String message;
}
