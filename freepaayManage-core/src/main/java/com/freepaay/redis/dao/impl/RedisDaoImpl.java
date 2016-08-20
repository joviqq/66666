package com.freepaay.redis.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.freepaay.common.LoggerAdapter;
import com.freepaay.common.LoggerAdapterFacory;
import com.freepaay.exception.FreepaayException;
import com.freepaay.redis.dao.RedisDao;

public class RedisDaoImpl<T> implements RedisDao<T>
{
    @Autowired
    private RedisTemplate<String, T> jedisTemplate;

    private static LoggerAdapter log = LoggerAdapterFacory.getLoggerAdapter(RedisDaoImpl.class);
    
    public void setObject(String key, T value) throws FreepaayException
    {
        try
        {
            ValueOperations<String, T> vps = jedisTemplate.opsForValue();
            vps.set(key, value);
          
        }
        catch (Exception ex)
        {
            String msg = "插入redis key:" + key + " value:" + value + "失败！";
            log.error(msg, ex);
            throw new FreepaayException(msg, ex);
        }
        
    }
    
    public void setObjectWithExpiredTime(String key, T value, int expiredTime) throws FreepaayException
    {
        if (key == null)
            throw new FreepaayException("重置reids值失败！");
        try
        {
            ValueOperations<String, T> vps = jedisTemplate.opsForValue();
            vps.set(key, value, expiredTime, TimeUnit.SECONDS);
        }
        catch (Exception ex)
        {
            String msg = "set key值为：" + key + "失败！";
            log.error(msg, ex);
            throw new FreepaayException(msg, ex);
        }
    }
    
    public T getObject(String key) throws FreepaayException
    {
        try
        {
            ValueOperations<String, T> vps = jedisTemplate.opsForValue();
            return vps.get(key);
        }
        catch (Exception ex)
        {
            String msg = "获取key值为：" + key + "对象失败！";
            log.error(msg, ex);
            throw new FreepaayException(msg, ex);
        }
    }
    
    public int countKey(String pattern) throws FreepaayException
    {
        try
        {
            Set<String> keyset = jedisTemplate.keys(pattern);
            return keyset.size();
        }
        catch (Exception ex)
        {
            String msg = "获取key值为：" + pattern + "数量失败！";
            log.error(msg, ex);
            throw new FreepaayException(msg, ex);
        }
    }
    
    public Set<String> getKeySet(String pattern) throws FreepaayException
    {
        try
        {
            Set<String> keyset = jedisTemplate.keys(pattern);
            return keyset;
        }
        catch (Exception ex)
        {
            String msg = "获取key值为：" + pattern + "数量失败！";
            log.error(msg, ex);
            throw new FreepaayException(msg, ex);
        }
    }
    
    public List<T> mgetObjects(Collection<String> keys) throws FreepaayException
    {
        try
        {
            ValueOperations<String, T> vps = jedisTemplate.opsForValue();
            return vps.multiGet(keys);
        }
        catch (Exception ex)
        {
            String msg = "获取key值为：" + keys + "对象失败！";
            log.error(msg, ex);
            throw new FreepaayException(msg, ex);
        }
    }
    
    public void maddObjects(Map<String, T> objMap) throws FreepaayException
    {
        try
        {
            ValueOperations<String, T> vps = jedisTemplate.opsForValue();
            vps.multiSet(objMap);
        }
        catch (Exception ex)
        {
            String msg = "插入对象" + objMap + "失败！";
            log.error(msg, ex);
            throw new FreepaayException(msg, ex);
        }
        
    }
    
    public List<Object> clearObject(final String... keys) throws FreepaayException
    {
        
        List<Object> txResults = null;
        try
        {
            txResults = jedisTemplate.execute(new SessionCallback<List<Object>>()
            {
                @SuppressWarnings({"unchecked", "rawtypes"})
                public List<Object> execute(RedisOperations operations) throws DataAccessException
                {
                    operations.multi();
                    for (String key : keys)
                    {
                        operations.delete(key);
                    }
                    return operations.exec();
                }
            });
        }
        catch (Exception ex)
        {
            String msg = "清除redis key " + keys + "失败！";
            log.error(msg, ex);
            throw new FreepaayException(msg, ex);
        }
        return txResults;
    }
    
    public boolean resetExpireTime(String key, int expireTime) throws FreepaayException
    {
        try
        {
            return jedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
        catch (Exception ex)
        {
            String msg = "重置key" + key + "过期时间失败！";
            log.error(msg, ex);
            throw new FreepaayException(msg, ex);
        }
    }
	
	@Override
	public void deleteObject(String key){
		jedisTemplate.delete(key);
	}
	
}
