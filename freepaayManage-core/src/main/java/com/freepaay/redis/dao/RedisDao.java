package com.freepaay.redis.dao;

import java.util.Collection; 
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.freepaay.exception.FreepaayException;


public interface RedisDao<T> 
{
    /**
     * 设置redis数据
     * @param key
     * @param value
     * @throws FreepaayException [参数说明]
     */
    public void setObject(String key,T value) throws FreepaayException;
    /**
     * 设置reids数据（ 有过期时间）
     * @param key
     * @param value
     * @param expiredTime
     * @throws FreepaayException [参数说明]
     */
    public void setObjectWithExpiredTime(String key,T value,int expiredTime) throws FreepaayException;
    /**
     * 获取redis数据
     * @param key
     * @return
     * @throws FreepaayException [参数说明]
     */
    public T getObject(String key) throws FreepaayException;
    /**
     * 查询redis中key
     * @param pattern
     * @return
     * @throws FreepaayException [参数说明]
     */
    public int countKey(String pattern) throws FreepaayException;
    
    /**
     * <pre>
     * 获取key set
     * </pre> 
     * @param pattern
     * @return        
     * @throws FreepaayException [参数说明]
     */
    public  Set<String> getKeySet(String pattern) throws FreepaayException;
    /**
     * 获取reids数据（多个）
     * @param keys
     * @return
     * @throws FreepaayException [参数说明]
     */
    public List<T> mgetObjects(Collection<String> keys ) throws FreepaayException;
    /**
     * 添加reids中数据（多个）
     * @param objMap
     * @throws FreepaayException [参数说明]
     */
    public void maddObjects(Map<String,T> objMap) throws FreepaayException;
    
    /**
     * 清除redis中key
     * @param keys
     * @return
     * @throws FreepaayException [参数说明]
     */
    public List<Object> clearObject(final String... keys) throws FreepaayException;
    
    /**
     * 重置KEY过期时间
     * @param key
     * @param expireTime
     * @throws FreepaayException [参数说明]
     */
    public boolean resetExpireTime(String key,int expireTime) throws FreepaayException;
    
    /**
     * 删除数据redis数据
     * @param key
     */
	void deleteObject(String key);
}
