package com.dzj.miaosha.redis;

public class GoodsKey extends BassPrefix{

	private GoodsKey(String prefix) {
		super(0, prefix);
	}

	public static GoodsKey getById =new GoodsKey("goodsId");
	public static GoodsKey getGoodsList =new GoodsKey("goodsList");
	
	
}
