package com.dzj.miaosha.service;

import java.util.List;

import com.dzj.miaosha.vo.GoodsVo;

public interface GoodsService {

	/**
	 * 获取所有商品信息
	 * @return
	 */
	public List<GoodsVo> getGoodsList();
	
	/**
	 * 根据商品id查询商品
	 * @param goodsId
	 * @return
	 */
	public GoodsVo getGoodsByGoodsId(long goodsId);
	
	/**
	 * 减少库存
	 * @param goodsId
	 * @return
	 */
	public int reduceStock(long goodsId);
}
