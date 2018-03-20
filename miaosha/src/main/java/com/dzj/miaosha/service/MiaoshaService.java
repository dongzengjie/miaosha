package com.dzj.miaosha.service;

import com.dzj.miaosha.dto.Expose;
import com.dzj.miaosha.entity.OrderInfo;
import com.dzj.miaosha.entity.User;
import com.dzj.miaosha.exception.MiaoshaException;
import com.dzj.miaosha.vo.GoodsVo;

public interface MiaoshaService {

	/**
	 * 执行秒杀
	 * @param user
	 * @param goodsVo
	 * @return
	 */
	public OrderInfo miaosha(User user ,GoodsVo goodsVo,String Key_md5);
	
	/**
	 * 获取秒杀地址接口密钥
	 * @param goodsId
	 * @return
	 */
	public Expose getMiaosha_Key(Long goodsId);
	
	public void advancereduceStocks(User user, GoodsVo goodsVo,String Key_md5) throws MiaoshaException;
	
	public long getMiaoshaResult(Long userId, long goodsId); 
}
