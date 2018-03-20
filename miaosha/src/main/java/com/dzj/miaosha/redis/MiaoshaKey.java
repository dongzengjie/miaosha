package com.dzj.miaosha.redis;

public class MiaoshaKey extends BassPrefix {

	public static final int TOKEN_EXPIRE=3600  ;
	
	private MiaoshaKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
		// TODO Auto-generated constructor stub
	}
	
	public static MiaoshaKey isGoodsOver =new MiaoshaKey(0, "goodsover");

}
