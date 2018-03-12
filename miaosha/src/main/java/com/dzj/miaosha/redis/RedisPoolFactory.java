package com.dzj.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {
	@Autowired
	private RedisConfig config;
	
	@Bean
	public JedisPool JedisPoolFactory() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

		jedisPoolConfig.setMaxIdle(config.getPoolMaxIdle());
		jedisPoolConfig.setMaxTotal(config.getPoolMaxTotal());
		jedisPoolConfig.setMaxWaitMillis(config.getPoolMaxWait() * 1000);

		JedisPool jedisPool = new JedisPool(jedisPoolConfig, config.getHost(), config.getPort(),
				config.getTimeout() * 1000, config.getPassword(), 0);
		return jedisPool;

	}
}
