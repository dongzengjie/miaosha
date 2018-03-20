package com.dzj.miaosha.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzj.miaosha.redis.RedisService;
import com.dzj.miaosha.service.GoodsService;
import com.dzj.miaosha.service.MiaoshaService;
import com.dzj.miaosha.service.OrderService;

@Service
public class MQReceiver {

	@Autowired
	RedisService redisService;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	MiaoshaService miaoshaService;
	
	private static Logger logger =LoggerFactory.getLogger(MQReceiver.class);
	
	@RabbitListener(queues=MQConfig.QUEUE)
	public void receive(String message) {
		logger.info("receive msg :" +message);
	}
	
	/**
	 * 出队 处理秒杀逻辑
	 * @param message
	 */
	@RabbitListener(queues=MQConfig.MIAOSHA_QUEUE)
	public void miaoshaReceive(String message) {
		logger.info("receive msg :" +message);
		MiaoshaMessage miaoshaMessage =RedisService.stringToBean(message, MiaoshaMessage.class);	
		miaoshaService.miaosha(miaoshaMessage.getUser(), miaoshaMessage.getGoodsVo(), miaoshaMessage.getMd5());
		
	}
}
