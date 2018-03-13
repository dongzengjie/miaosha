package com.dzj.miaosha.redis;

public class MiaoshaUserKey extends BassPrefix {

	public static final int TOKEN_EXPIRE=3600 * 24 ;
	
	private MiaoshaUserKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
		// TODO Auto-generated constructor stub
	}
	
	public static MiaoshaUserKey token =new MiaoshaUserKey(TOKEN_EXPIRE, "tk");

}
