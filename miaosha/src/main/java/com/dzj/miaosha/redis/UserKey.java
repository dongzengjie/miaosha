package com.dzj.miaosha.redis;

public class UserKey extends BassPrefix{

	private UserKey(String prefix) {
		super(0, prefix);
	}

	private UserKey(int time ,String prefix) {
		super(time, prefix);
	}
	public static UserKey getById =new UserKey(3000,"id");
	public static UserKey getByName =new UserKey("userName");
	
}
