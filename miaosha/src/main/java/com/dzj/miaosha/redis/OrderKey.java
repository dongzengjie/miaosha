package com.dzj.miaosha.redis;

public class OrderKey extends BassPrefix{

	private OrderKey(String prefix) {
		super(0, prefix);
	}

	public static OrderKey getMiaoshaOrderByUserIdAndGoodsId =new OrderKey("getMiaoshaOrderByUserIdAndGoodsId");
	
	
	
}
