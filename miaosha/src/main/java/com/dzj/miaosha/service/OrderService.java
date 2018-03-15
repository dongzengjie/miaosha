package com.dzj.miaosha.service;

import com.dzj.miaosha.entity.MiaoshaOrder;
import com.dzj.miaosha.entity.OrderInfo;
import com.dzj.miaosha.entity.User;
import com.dzj.miaosha.vo.GoodsVo;

public interface OrderService {

	/**
	 * 根据用户id和商品id查询秒杀订单信息
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	public MiaoshaOrder getMiaoshaOrderByUserIdAndGoodsId(long userId,long goodsId);
	
	
	public OrderInfo createOrder(User user ,GoodsVo goodsVo);
	
	
}
