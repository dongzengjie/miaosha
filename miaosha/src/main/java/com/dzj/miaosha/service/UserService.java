package com.dzj.miaosha.service;

import javax.servlet.http.HttpServletResponse;

import com.dzj.miaosha.entity.User;
import com.dzj.miaosha.exception.MiaoshaException;
import com.dzj.miaosha.vo.LoginVo;

public interface UserService {

	/**
	 * 根据id查询用户
	 * @param userId
	 * @return
	 */
	public User getByUserId(long userId);
	
	/**
	 * 从Redis缓存中根据token获取用户信息，并将cookie有效期延长（分布式session）
	 * @param response
	 * @param token
	 * @return
	 */
	public User getByTokenFromRedisService(HttpServletResponse response,String token);
	
	/**
	 * 执行登陆操作，将用户信息放入缓存和cookie中（分布式session）
	 * @param response
	 * @param loginVo
	 * @return
	 */
	public String login(HttpServletResponse response,LoginVo loginVo) throws MiaoshaException;
}
