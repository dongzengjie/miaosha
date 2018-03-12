package com.dzj.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzj.miaosha.redis.RedisService;
import com.dzj.miaosha.redis.UserKey;
import com.dzj.miaosha.result.Result;

@Controller
public class Test {

	@Autowired
	private RedisService redisservice;
	
	@RequestMapping(value="/getredis")
	@ResponseBody
	public Result<Long> getredis() {
		Long v1=redisservice.get(UserKey.getById,""+1, Long.class);
		return Result.success(v1);
		
	}
	
	@RequestMapping(value="/setredis")
	@ResponseBody
	public Result<String> setredis() {
		redisservice.set(UserKey.getById,""+1, "d3434sdasd");
		String v1=redisservice.get(UserKey.getById,""+1, String.class);
		
		return Result.success(v1);
		
	}
}
