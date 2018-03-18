package com.dzj.miaosha.service.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzj.miaosha.dto.Expose;
import com.dzj.miaosha.emuns.GlobalEnums;
import com.dzj.miaosha.entity.MiaoshaOrder;
import com.dzj.miaosha.entity.OrderInfo;
import com.dzj.miaosha.entity.User;
import com.dzj.miaosha.exception.MiaoshaException;
import com.dzj.miaosha.redis.GoodsKey;
import com.dzj.miaosha.redis.RedisService;
import com.dzj.miaosha.service.GoodsService;
import com.dzj.miaosha.service.MiaoshaService;
import com.dzj.miaosha.service.OrderService;
import com.dzj.miaosha.util.MD5Util;
import com.dzj.miaosha.vo.GoodsVo;

@Service
public class MiaoshaServiceImpl implements MiaoshaService {

	private final String md5stingr = "*asd78qw6a5sRTHG//HG{}{;&*(*67";

	@Autowired
	private GoodsService goodsService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private RedisService redisService;

	@Transactional
	public OrderInfo miaosha(User user, GoodsVo goodsVo,String Key_md5) throws MiaoshaException {

		
		if(Key_md5 ==null || ! Key_md5.equals(MD5Util.md5(goodsVo.getGoodsId()+md5stingr))) {
			throw new MiaoshaException("密钥已被修改", GlobalEnums.Server_ERROR.getState());
		}
		
		OrderInfo orderInfo = orderService.createOrder(user, goodsVo);

		int effect =goodsService.reduceStock(goodsVo.getGoodsId());
		if(effect <=0) {
			throw new MiaoshaException("秒杀已经结束", GlobalEnums.Server_ERROR.getState());
		}
		return orderInfo;
	}

	/**
	 * 获取秒杀接口
	 */
	public Expose getMiaosha_Key(Long goodsId) {
		GoodsVo goods =null;
		 goods = redisService.get(GoodsKey.getById, goodsId.toString(), GoodsVo.class);
		if(goods==null) {
			goods= goodsService.getGoodsByGoodsId(goodsId);
			redisService.set(GoodsKey.getById, goodsId.toString(), goods);
		}
		if(goods ==null) {
			return new Expose(false, goodsId);
		}
		Date startTime = goods.getStartDate();
		Date endTime = goods.getEndDate();
		Date nowTime = new Date();
		
		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Expose(false, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		String Key_md5=MD5Util.md5(goodsId+md5stingr);
		return new Expose(Key_md5, true, goodsId);
	}

}
