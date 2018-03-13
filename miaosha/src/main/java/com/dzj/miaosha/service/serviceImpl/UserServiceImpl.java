package com.dzj.miaosha.service.serviceImpl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.dzj.miaosha.entity.User;
import com.dzj.miaosha.service.UserService;
import com.dzj.miaosha.vo.LoginVo;

@Service
public class UserServiceImpl implements UserService {

	
	public User getByUserId(long userId) {
		// TODO Auto-generated method stub
		return null;
	}


	public User getByTokenFromRedisService(HttpServletResponse response, String token) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean login(HttpServletResponse response, LoginVo loginVo) {
		// TODO Auto-generated method stub
		return false;
	}

}
