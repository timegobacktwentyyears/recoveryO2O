package com.jt.common.service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

//@Service
public class RedisSentinelService {
	private Logger logger = LoggerFactory.getLogger("RedisSentinelService"); 

	//有的工程需要，有的工程不需要。设置required=false，有就注入，没有就不注入。
    @Autowired(required = false)
    RedisTemplate<?, ?> redisTemplate;  
  
    // 线程池  
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(  
            256, 256, 30L, TimeUnit.SECONDS,  
            new LinkedBlockingQueue<Runnable>(),  
            new BasicThreadFactory.Builder().daemon(true)  
                    .namingPattern("redis-oper-%d").build(),  
            new ThreadPoolExecutor.CallerRunsPolicy());  
  
    public void set(final String key, final String value) {  
        redisTemplate.execute(new RedisCallback<Object>() {  
            public Object doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                connection.set(  
                        redisTemplate.getStringSerializer().serialize(key),  
                        redisTemplate.getStringSerializer().serialize(value));  
                return null;  
            }  
        });  
    }  
    
    //设置值后并设置过期时间
    public void set(final String key, final String value, final long seconds) {  
    	redisTemplate.execute(new RedisCallback<Object>() {  
    		 
    		public Object doInRedis(RedisConnection connection)  
    				throws DataAccessException {  
    			connection.set(  
    					redisTemplate.getStringSerializer().serialize(key),  
    					redisTemplate.getStringSerializer().serialize(value));  
    			connection.expire(redisTemplate.getStringSerializer().serialize(key), seconds);
    			return null;  
    		}  
    	});  
    }  
  
    public String get(final String key) {  
        return redisTemplate.execute(new RedisCallback<String>() {  
              
            public String doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                byte[] byteKye = redisTemplate.getStringSerializer().serialize(  
                        key);  
                if (connection.exists(byteKye)) {  
                    byte[] byteValue = connection.get(byteKye);  
                    String value = redisTemplate.getStringSerializer()  
                            .deserialize(byteValue);  
                    logger.debug("get key:" + key + ",value:" + value);  
                    return value;  
                }  
                logger.error("valus does not exist!,key:"+key);  
                return null;  
            }  
        });  
    }  
  
    public void delete(final String key) {  
        redisTemplate.execute(new RedisCallback<Object>() {  
            public Object doInRedis(RedisConnection connection) {  
                connection.del(redisTemplate.getStringSerializer().serialize(  
                        key));  
                return null;  
            }  
        });  
    }  
  
    /** 
     * 线程池并发操作redis 
     *  
     * @param keyvalue 
     */  
    public void mulitThreadSaveAndFind(final String keyvalue) {  
        executor.execute(new Runnable() {  
              
            public void run() {  
                try {  
                    set(keyvalue, keyvalue);  
                    get(keyvalue);  
                } catch (Throwable th) {  
                    // 防御性容错，避免高并发下的一些问题  
                    logger.error("", th);  
                }  
            }  
        });  
    } 
}
