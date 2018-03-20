package com.dzj.miaosha.rabbitmq;

import com.dzj.miaosha.entity.User;
import com.dzj.miaosha.vo.GoodsVo;

public class MiaoshaMessage {

	private String md5;
	private User user;
	private GoodsVo goodsVo;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public GoodsVo getGoodsVo() {
		return goodsVo;
	}
	public void setGoodsVo(GoodsVo goodsVo) {
		this.goodsVo = goodsVo;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	 
}
