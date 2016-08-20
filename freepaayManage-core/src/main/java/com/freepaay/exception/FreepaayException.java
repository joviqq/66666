/*
 * 文 件 名:  LanTuException.java
 * 描    述:  自定义异常
 * 创 建 人:  pfma
 * 创建时间:  2014年3月21日
 * 修改内容:  <修改内容>
 */
package com.freepaay.exception;


/**
 * 
 * <pre>
 * 自定义异常
 * 
 * @author  福福
 * @data  2016年6月16日
 */
public class FreepaayException extends Exception
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 800263098528696897L;
    
    public FreepaayException(String msg)
    {
        super(msg);
    }
    
    public FreepaayException(String msg, Throwable ex)
    {
        super(msg, ex);
    }
    
    public FreepaayException(Throwable ex)
    {
        super(ex);
    }
}
