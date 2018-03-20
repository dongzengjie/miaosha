package com.dzj.miaosha.service.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzj.miaosha.dto.Expose;
import com.dzj.miaosha.emuns.GlobalEnums;
import com.dzj.miaosha.entity.MiaoshaOrder;
import com.dzj.miaosha.entity.OrderInfo;
import com.dzj.miaosha.entity.User;
import com.dzj.miaosha.exception.MiaoshaException;
import com.dzj.miaosha.rabbitmq.MQReceiver;
import com.dzj.miaosha.redis.GoodsKey;
import com.dzj.miaosha.redis.MiaoshaKey;
import com.dzj.miaosha.redis.RedisService;
import com.dzj.miaosha.service.GoodsService;
import com.dzj.miaosha.service.MiaoshaService;
import com.dzj.miaosha.service.OrderService;
import com.dzj.miaosha.util.MD5Util;
import com.dzj.miaosha.vo.GoodsVo;

@Service
public class MiaoshaServiceImpl implements MiaoshaService,InitializingBean {

	private final String md5stingr = "*asd78qw6a5sRTHG//HG{}{;&*(*67";
	private static Logger logger =LoggerFactory.getLogger(MiaoshaServiceImpl.class);
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private RedisService redisService;

	private HashMap<Long, Boolean> localOverMap =  new HashMap<Long, Boolean>();
	
	@Transactional
	public OrderInfo miaosha(User user, GoodsVo goodsVo,String Key_md5) throws MiaoshaException {

		
		if(Key_md5 ==null || ! Key_md5.equals(MD5Util.md5(goodsVo.getGoodsId()+md5stingr))) {
			throw new MiaoshaException("密钥已被修改", GlobalEnums.Server_ERROR.getState());
		}
		
		

		boolean effect =goodsService.reduceStock(goodsVo.getGoodsId());
		if(effect) {
			OrderInfo orderInfo = orderService.createOrder(user, goodsVo);
			return orderInfo;
		}else {
			setGoodsOver(goodsVo.getGoodsId());
			return null;
		}
	
	}
	
	/**
	 * 预减库存
	 * @param user
	 * @param goodsVo
	 * @param Key_md5
	 * @return
	 * @throws MiaoshaException
	 */
	public void advancereduceStocks(User user, GoodsVo goodsVo,String Key_md5) throws MiaoshaException {
		
		if(Key_md5 ==null || ! Key_md5.equals(MD5Util.md5(goodsVo.getGoodsId()+md5stingr))) {
			throw new MiaoshaException("密钥已被修改", GlobalEnums.Server_ERROR.getState());
		}
		
		boolean over = localOverMap.get(goodsVo.getGoodsId());
    	if(over) {
    		throw new MiaoshaException("库存不足", -1111);
    	}
		
		long stocks =redisService.decr(GoodsKey.getGoodsStocks, ""+goodsVo.getGoodsId());
		logger.info(stocks+"");
		if(stocks < 0) {
			 localOverMap.put(goodsVo.getGoodsId(), true);
			 throw new MiaoshaException("库存不足", -1111);
		}
		MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdAndGoodsId(user.getUserId(), goodsVo.getGoodsId());
    	if(order != null) {
    		throw new MiaoshaException("重复秒杀", -555);
    	}
    	
	
		
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
	
	private void setGoodsOver(Long goodsId) {
		redisService.set(MiaoshaKey.isGoodsOver, ""+goodsId, true);
	}
	
	private boolean getGoodsOver(long goodsId) {
		return redisService.exists(MiaoshaKey.isGoodsOver, ""+goodsId);
	}


	/**
	 * 轮询判断是否秒杀成功
	 */
	public long getMiaoshaResult(Long userId, long goodsId) {
		MiaoshaOrder miaoshaOrder =orderService.getMiaoshaOrderByUserIdAndGoodsId(userId, goodsId);
		if(miaoshaOrder != null ) {
			//秒杀成功
			return miaoshaOrder.getMiaoshaOrderId();
		}else {
			boolean isover =getGoodsOver(goodsId);
			if(isover) {
				return -1;
			}else {
				return 0;	
			}
		}
	
	}

	/**
	 * 系统初始化
	 * @throws Exception
	 */
	public void afterPropertiesSet() throws Exception {
		List<GoodsVo> goodsList =  goodsService.getGoodsList();
		
		if(goodsList == null) {
			return;
		}
		
		for (GoodsVo goodsVo : goodsList) {
			redisService.set(GoodsKey.getGoodsStocks, ""+goodsVo.getGoodsId(), goodsVo.getStockCount());
			logger.info("goodsvo"+goodsVo.getGoodsId()+":" + goodsVo.getStockCount());
			localOverMap.put(goodsVo.getGoodsId(), false);
		}
		
	}
}
