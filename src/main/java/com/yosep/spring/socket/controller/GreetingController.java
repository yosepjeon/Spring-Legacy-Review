package com.yosep.spring.socket.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.yosep.spring.socket.model.dto.Greeting;
import com.yosep.spring.socket.model.dto.HelloMessage;

@Controller
public class GreetingController {
	@MessageMapping("/TTT")
	@SendTo("/topic/message")
	public String tttMessage(String message) {
		System.out.println("TTT>> " + message);
		
		return message;
	}
}
