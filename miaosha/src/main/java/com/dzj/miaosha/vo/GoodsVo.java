package com.dzj.miaosha.vo;

import java.util.Date;

import com.dzj.miaosha.entity.Goods;

public class GoodsVo extends Goods {

	private double miaoshaPrice;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;
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
	
	
}
