package com.dzj.miaosha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/goods")
public class ToGoodsHtml {
	
	@GetMapping(value="/toindex")
	public String toindex() {
		return "index";
		
	}
	
	@GetMapping(value="/todetail")
	public String todetail() {
		return "detail";
		
	}
}
