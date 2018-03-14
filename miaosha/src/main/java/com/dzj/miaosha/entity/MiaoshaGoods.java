package com.dzj.miaosha.entity;

import java.util.Date;

public class MiaoshaGoods {

	private Long miaoshaGoodsId;
	private Long goodsId;
	private double miaoshaPrice;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;
	public Long getMiaoshaGoodsId() {
		return miaoshaGoodsId;
	}
	public void setMiaoshaGoodsId(Long miaoshaGoodsId) {
		this.miaoshaGoodsId = miaoshaGoodsId;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public double getMiaoshaPrice() {
		return miaoshaPrice;
	}
	public void setMiaoshaPrice(double miaoshaPrice) {
		this.miaoshaPrice = miaoshaPrice;
	}

	
	
	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "MiaoshaGoods [miaoshaGoodsId=" + miaoshaGoodsId + ", goodsId=" + goodsId + ", miaoshaPrice="
				+ miaoshaPrice + ", stockCount=" + stockCount + ", startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}
	
	
}
