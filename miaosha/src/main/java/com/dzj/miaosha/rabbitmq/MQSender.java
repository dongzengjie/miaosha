package com.dzj.miaosha.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzj.miaosha.redis.RedisService;

@Service
public class MQSender {
	private static Logger logger =LoggerFactory.getLogger(MQSender.class);
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send(Object message) {
		String msg = RedisService.beanToString(message);
		logger.info("send msg :" +msg);
		amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);
	}
	
	
	public void miaoshaSend(Object message) {
		String msg = RedisService.beanToString(message);
		logger.info("send msg :" +msg);
		amqpTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE, msg);
	}
}
