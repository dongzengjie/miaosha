package com.dzj.miaosha.dto;

public class Expose {
	

	private String KEY_MD5;
	
	private boolean exposed;
	private long goodsId;
	
	private long now;
	private long start;
	private long end;
	public String getKEY_MD5() {
		return KEY_MD5;
	}
	public void setKEY_MD5(String kEY_MD5) {
		KEY_MD5 = kEY_MD5;
	}
	public boolean isExposed() {
		return exposed;
	}
	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}
	
	
	public long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
	public long getNow() {
		return now;
	}
	public void setNow(long now) {
		this.now = now;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	/**
	 * 秒杀未开始
	 * @param exposed
	 * @param seckillId
	 * @param now
	 * @param start
	 * @param end
	 */
	public Expose( boolean exposed, long now, long start, long end) {
		super();
		
		this.exposed = exposed;
		
		this.now = now;
		this.start = start;
		this.end = end;
	}
	/**
	 * 秒杀开启
	 * @param kEY_MD5
	 * @param exposed
	 * @param seckillId
	 */
	public Expose(String kEY_MD5, boolean exposed, long goodsId) {
		super();
		KEY_MD5 = kEY_MD5;
		this.exposed = exposed;
		this.goodsId = goodsId;
	}
	public Expose(boolean exposed, long goodsId) {
		super();
		this.exposed = exposed;
		this.goodsId = goodsId;
	}
	
	
	
	
}
