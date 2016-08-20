package com.freepaay.manage.redis.service;

import org.apache.poi.ss.formula.functions.T;

import com.freepaay.exception.FreepaayException;

public interface RedisService<T> {

	void setObject(String key, T value) throws FreepaayException;
	
	void setObjectWithExpireTime(String key, T value, int time) throws FreepaayException;

	T getObject(String key) throws FreepaayException;

	void deleteObject(String key) throws FreepaayException;

}
