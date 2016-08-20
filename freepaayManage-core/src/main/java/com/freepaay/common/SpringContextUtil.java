package com.freepaay.common; 

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <pre>
 * 获取sping容器的工具类，可以访问容器中定义的其他bean
 * </pre>
 * 
 * @author 福福
 */
public class SpringContextUtil implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;
    
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        SpringContextUtil.applicationContext = applicationContext;
    }
    
    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }
    
    public static Object getBean(String name) throws BeansException
    {
        return applicationContext.getBean(name);
    }
    
    public static Object getBean(String name, Class<T> requiredType) throws BeansException
    {
        return applicationContext.getBean(name, requiredType);
    }
}
