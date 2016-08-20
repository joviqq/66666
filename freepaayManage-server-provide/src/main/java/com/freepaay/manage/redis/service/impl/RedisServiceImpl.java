package com.freepaay.manage.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freepaay.exception.FreepaayException;
import com.freepaay.manage.redis.service.RedisService;
import com.freepaay.redis.dao.RedisDao;


@Service(value = "redisService")
public class RedisServiceImpl<T> implements RedisService<T> {

	@Autowired
	private  RedisDao<T> redisDaoString;

	/* set缓存操作 */
	@Override
	public void setObject(String key, T value) throws FreepaayException {
		redisDaoString.setObject(key, value);
		System.out.println("============Set Redis " + key + "============");
	}

	/* set缓存操作带超时 */
	@Override
	public void setObjectWithExpireTime(String key,T value,int time) throws FreepaayException {
		redisDaoString.setObjectWithExpiredTime(key, value, time);
		System.out.println("============Set Redis " + key + "============");
	}

	/* get缓存操作 */
	@Override
	public T getObject(String key) throws FreepaayException {
		System.out.println("============get Redis " + key + "============");
		return redisDaoString.getObject(key);
	}

	/* delete缓存操作 */
	@Override
	public void deleteObject(String key) throws FreepaayException {
		redisDaoString.deleteObject(key);
		System.out.println("============delete Redis " + key + "============");
	}

}
