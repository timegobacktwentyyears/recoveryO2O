package com.jt.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service//将当前对象交给spring管理
public class ShardRedisService {

	@Autowired(required = false) //需要时才进行操作
	private ShardedJedisPool shardedJedisPool;
	
	public String set(String key ,String value){
		//获取jedis对象
		ShardedJedis jedis = shardedJedisPool.getResource();
		//执行set操作
		String result = jedis.set(key, value);
		//将连接返回个连接池
		shardedJedisPool.returnResource(jedis);
		return result;
	}
	public String get(String key){
		//获取jedis对象
		ShardedJedis shardedJedis=shardedJedisPool.getResource();
		
		//实现get操作
		String result = shardedJedis.get(key);
		
		//将连接返回给连接池
		shardedJedisPool.returnResource(shardedJedis);
		
		return result;
	}
	
	
	
}
