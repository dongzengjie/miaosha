 package com.dzj.miaosha.redis;

public abstract class BassPrefix implements KeyPrefix{

	private int expireSeconds;
	private String prefix;
	
	public BassPrefix(int expireSeconds, String prefix) {
	
		this.expireSeconds = expireSeconds;
		this.prefix = prefix;
	}
	
	public BassPrefix( String prefix) {
		
		this(0,prefix);
	}

	//默认 0代表不会过期
	public int expireSeconds() {
		
		return expireSeconds;
	}


	public String getPrefix() {
		String className=getClass().getSimpleName();
		return className +":"+prefix;
	}

}
