package com.dzj.miaosha.dao;

import org.apache.ibatis.annotations.Mapper;

import com.dzj.miaosha.entity.User;

@Mapper
public interface UserDao {
	
	/**
	 * 更具手机号码查询用户
	 * @param userId
	 * @return
	 */
	public User getUserById(Long userId);
	
	
}
