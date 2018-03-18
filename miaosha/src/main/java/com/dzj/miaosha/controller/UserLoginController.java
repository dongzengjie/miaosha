package com.dzj.miaosha.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dzj.miaosha.result.Result;
import com.dzj.miaosha.service.UserService;
import com.dzj.miaosha.vo.LoginVo;

@RestController
@RequestMapping(value="/login")
public class UserLoginController {
	private static Logger log = LoggerFactory.getLogger(UserLoginController.class);
	@Autowired
	private UserService userService;
	
	@PostMapping(value="do_login")
	public Result<String> do_login(HttpServletResponse response, @Valid LoginVo loginVo) {
		
		String token =userService.login(response, loginVo);
		log.debug("登陆"+loginVo.getMobile());
		return Result.success(token);
		
	}
}
