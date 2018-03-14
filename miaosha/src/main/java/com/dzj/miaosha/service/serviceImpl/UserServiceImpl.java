package com.dzj.miaosha.service.serviceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzj.miaosha.dao.UserDao;
import com.dzj.miaosha.emuns.UserEnum;
import com.dzj.miaosha.entity.User;
import com.dzj.miaosha.exception.MiaoshaException;
import com.dzj.miaosha.redis.MiaoshaUserKey;
import com.dzj.miaosha.redis.RedisService;
import com.dzj.miaosha.service.UserService;
import com.dzj.miaosha.util.MD5Util;
import com.dzj.miaosha.util.UUIDUtil;
import com.dzj.miaosha.vo.LoginVo;

@Service
public class UserServiceImpl implements UserService {

	private final String COOKIER_NAME_TOKEN="token";
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private UserDao userDao;
	
	public User getByUserId(long userId) {
		
		return userDao.getUserById(userId);
	}


	public User getByTokenFromRedisService(HttpServletResponse response, String token) {
	
		if(StringUtils.isEmpty(token)) {
			return null;
		}
		
		User user =redisService.get(MiaoshaUserKey.token, token, User.class);
		//延长cookie有效期
		if(user !=null) {
			addCookie(response,token,user);
		}
		return user;
	}

	
	private void addCookie(HttpServletResponse response,String token, User user) {
		redisService.set(MiaoshaUserKey.token, token, user);
		Cookie cookie =new Cookie(COOKIER_NAME_TOKEN, token);
		cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
		cookie.setPath("/");
		response.addCookie(cookie);
	}


	public String login(HttpServletResponse response, LoginVo loginVo) throws MiaoshaException{
		
		if(loginVo ==null) {
			throw new MiaoshaException(UserEnum.USER_LOGIN_ERROR.getStateInfo(),UserEnum.USER_LOGIN_ERROR.getState());
		}
		String mobile=loginVo.getMobile();
		String password =loginVo.getPassword();
		//根据mobile也就是userId查询user
		User user =this.getByUserId(Long.valueOf(mobile));
		if(user == null) {
			throw new MiaoshaException(UserEnum.USER_NOT_EXIST.getStateInfo(),UserEnum.USER_NOT_EXIST.getState());
		}
		//验证密码
		String dbpass=user.getPassword();
		String salt =user.getSalt();
		String calcPass = MD5Util.formPassToDBPass(password, salt);
		if(!calcPass.equals(dbpass)) {
			throw new MiaoshaException(UserEnum.PASSWORD_ERROR.getStateInfo(),UserEnum.PASSWORD_ERROR.getState());
		}
		//生成token
		String token = UUIDUtil.getUUID();
		addCookie(response, token, user);
		return token;
	}

}
