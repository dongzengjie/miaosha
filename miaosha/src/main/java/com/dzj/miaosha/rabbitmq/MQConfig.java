package com.dzj.miaosha.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

	public static final String QUEUE="queue";
	
	public static final String MIAOSHA_QUEUE="miaosha_queue";
	
	@Bean
	public Queue queue() {
		return new Queue(QUEUE,true);
	}
	
	
	@Bean
	public Queue miaoshaqueue() {
		return new Queue(MIAOSHA_QUEUE,true);
	}
}
