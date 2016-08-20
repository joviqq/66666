package com.freepaay.common;

/**
 * 日志对象工厂
 * 
 * @author 福福
 */
public class LoggerAdapterFacory
{
    @SuppressWarnings("rawtypes")
    public static LoggerAdapter getLoggerAdapter(Class clazz)
    {
        return new LogbackLogger(clazz);
    }
}
