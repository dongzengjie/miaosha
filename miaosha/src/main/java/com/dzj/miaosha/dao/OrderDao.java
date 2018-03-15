package com.dzj.miaosha.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dzj.miaosha.entity.MiaoshaOrder;
import com.dzj.miaosha.entity.OrderInfo;

@Mapper
public interface OrderDao {

	/**
	 * 获取秒杀订单
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	public MiaoshaOrder getMiaoshaOrderByUserIdAndGoodsId(@Param("userId") Long userId, @Param("goodsId") Long goodsId);
	
	/**
	 * 插入订单消息
	 * @param orderInfo
	 * @return
	 */
	public int insertOrderInfo(OrderInfo orderInfo);
	
	/**
	 * 插入秒杀订单消息 
	 * @param miaoshaOrder
	 * @return
	 */
	public int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}
