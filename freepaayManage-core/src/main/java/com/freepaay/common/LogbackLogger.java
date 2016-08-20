package com.freepaay.common;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

/**
 * 
 * <pre>
 * 一句话功能简述 
 * 功能详细描述
 * </pre>
 * 
 * @author  福福
 * @data  2016年6月8日
 */
public class LogbackLogger implements LoggerAdapter
{
    private Logger logger;
    
    @SuppressWarnings("rawtypes")
    public LogbackLogger(Class clazz)
    {
        this.logger = LoggerFactory.getLogger(clazz);
    }
    
    public void info(String msg)
    {
        logger.info(msg);
    }
    
    public void info(Throwable ex)
    {
        logger.info(ex.getMessage(), ex);
    }
    
    public void info(String msg, Throwable ex)
    {
        logger.info(msg, ex);
    }
    
    public void debug(String msg, Throwable ex)
    {
        logger.debug(msg, ex);
    }
    
    public void debug(String msg)
    {
        logger.debug(msg);
    }
    
    public void debug(Throwable ex)
    {
        logger.debug(ex.getMessage(), ex);
    }
    
    public void error(String msg, Throwable ex)
    {
        logger.error(msg, ex);
    }
    
    public void error(String msg)
    {
        logger.error(msg);
    }
    
    public void error(Throwable ex)
    {
        logger.error(ex.getMessage(), ex);
    }
    
    public void warn(String msg)
    {
        logger.warn(msg);
    }
    
    public void warn(Throwable ex)
    {
        logger.warn(ex.getMessage(), ex);
    }
    
    public void warn(String msg, Throwable ex)
    {
        logger.warn(msg, ex);
    }
    
}
