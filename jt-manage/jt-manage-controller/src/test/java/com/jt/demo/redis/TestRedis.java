package com.jt.demo.redis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.ShardInfo;

public class TestRedis {
	
	//测试远程连接redis是否成功
	//@Test
	public void test01(){
		//通过ip和端口号进行连接
		Jedis jedis = new Jedis("192.168.154.144", 6379);
		
		//先通过jedis为redis赋值
		String result = jedis.set("1707", "快要毕业了");
		System.out.println("redis的返回值:"+result);
		String data = jedis.get("1707");
		System.out.println("获取指定key的返回值:"+data);
	}
	
	
	//实现分片的操作
	//@Test
	public void test02(){
		//定义分片的集合    测试之前检查redis服务是否正常  端口号是否开放
		List<JedisShardInfo> infoList = new ArrayList<JedisShardInfo>();
		infoList.add(new JedisShardInfo("192.168.154.144", 6379));
		infoList.add(new JedisShardInfo("192.168.154.144", 6380));
		infoList.add(new JedisShardInfo("192.168.154.144", 6381));
		
		//定义jedis连接配置
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(100);
		
		//定义jedis连接池
		ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig, infoList);
		
		//从池中获取jedis对象
		ShardedJedis jedis = jedisPool.getResource();
		
		jedis.set("name", "今天中午吃什么?");
		String result = jedis.get("name");
		System.out.println("redis分片的结果为:"+result);
	}
	
	
	//@Test
	public void testSen(){
		Set<String> sentinels = new HashSet<String>();
		
		sentinels.add(new HostAndPort("192.168.154.144", 26379).toString());
		sentinels.add(new HostAndPort("192.168.154.144", 26380).toString());
		sentinels.add(new HostAndPort("192.168.154.144", 26380).toString());
		
		JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);
		
		Jedis jedis = pool.getResource();
		jedis.set("name", "1707班");
		System.out.println(jedis.get("name"));
	}
}
