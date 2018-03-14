package com.dzj.miaosha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/login")
public class ToLoginHtmlController {

	@GetMapping(value="/tologin")
	public String tologin() {
		return "login";
		
	}
	
	@GetMapping(value="/toindex")
	public String toindex() {
		return "index";
		
	}
}
