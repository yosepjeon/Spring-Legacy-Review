package com.yosep.spring.socket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/socket")
public class ViewSocketPageController {
	@RequestMapping(value="/page1")
	public ModelAndView viewPage1() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("stomp");
		
		return mav;
	}
}
