package com.dzj.miaosha.redis;

public class UserKey extends BassPrefix{

	private UserKey(String prefix) {
		super(0, prefix);
	}

	public static UserKey getById =new UserKey("id");
	public static UserKey getByName =new UserKey("userName");
	
}
