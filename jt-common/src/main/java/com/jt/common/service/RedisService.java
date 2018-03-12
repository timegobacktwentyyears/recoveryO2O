package com.jt.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
//@Service
public class RedisService {
	
	@Autowired
	private ShardedJedisPool shardedJedisPool;

	//设置key值
	public String setKey(String key,String value){
		ShardedJedis jedis = shardedJedisPool.getResource();
		String result = jedis.set(key, value);
		shardedJedisPool.returnResource(jedis);
		return result;
	}
	
	//获取key的value值
	public String getKey(String key){
		ShardedJedis jedis = shardedJedisPool.getResource();
		String result = jedis.get(key);
		shardedJedisPool.returnResource(jedis);
		return result;
	}
	
}
