package com.dzj.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzj.miaosha.rabbitmq.MQSender;
import com.dzj.miaosha.result.Result;

@Controller
public class Test {

	@Autowired
	private MQSender mqSender;
	
	@RequestMapping(value="/mq")
	@ResponseBody
	public String  mq() {
		mqSender.send("你好 董曾杰 ");
		return "aaaa";
	
	}
}
