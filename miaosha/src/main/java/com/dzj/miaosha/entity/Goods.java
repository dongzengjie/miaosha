package com.dzj.miaosha.entity;

public class Goods {

	private Long goodsId;
	private String goodName;
	private String goodsTitle;
	private String goodsImg;
	private String goodsDetail;
	private double goodsPrice;
	private int goodsStock;
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getGoodsTitle() {
		return goodsTitle;
	}
	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public String getGoodsDetail() {
		return goodsDetail;
	}
	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getGoodsStock() {
		return goodsStock;
	}
	public void setGoodsStock(int goodsStock) {
		this.goodsStock = goodsStock;
	}
	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodName=" + goodName + ", goodsTitle=" + goodsTitle + ", goodsImg="
				+ goodsImg + ", goodsDetail=" + goodsDetail + ", goodsPrice=" + goodsPrice + ", goodsStock="
				+ goodsStock + "]";
	}
	
	
}
