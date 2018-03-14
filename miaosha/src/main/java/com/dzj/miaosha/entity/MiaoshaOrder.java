package com.dzj.miaosha.entity;

public class MiaoshaOrder {
	private Long miaoshaOrderId;
	private Long userId;
	private Long  orderId;
	private Long goodsId;
	public Long getMiaoshaOrderId() {
		return miaoshaOrderId;
	}
	public void setMiaoshaOrderId(Long miaoshaOrderId) {
		this.miaoshaOrderId = miaoshaOrderId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	@Override
	public String toString() {
		return "MiaoshaOrder [miaoshaOrderId=" + miaoshaOrderId + ", userId=" + userId + ", orderId=" + orderId
				+ ", goodsId=" + goodsId + "]";
	}
	
	
}
