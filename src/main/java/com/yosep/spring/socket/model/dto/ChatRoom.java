package com.yosep.spring.socket.model.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ChatRoom {
	private String chatId;
	private String name;
	
	public static ChatRoom create(String chatId,String name) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setChatId(chatId);
		chatRoom.setName(name);
		
		return chatRoom;
	}
}
