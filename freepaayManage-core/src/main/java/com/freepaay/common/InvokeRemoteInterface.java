package com.freepaay.common;

import org.apache.commons.httpclient.HttpClient;  
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;


/**
 * <pre>
 * 多线程调用图片处理接口
 * 根据后期需要决定是否使用
 * @author  福福
 */
public class InvokeRemoteInterface extends Thread
{
	Logger log = Logger.getLogger(InvokeRemoteInterface.class); 
    private String type;
    
    private String pic;
    
    public InvokeRemoteInterface(String type, String pic)
    {
        super();
        this.type = type;
        this.pic = pic;
    }
    
    public void run()
    {
        String url = StaticVariables.IMAG_SERVER_PATH + "/handle";
        //url = "http://192.168.0.11:8080/file/handle";
        url = url + "?type=" + type + "&pic=" + pic;
        GetMethod getMethod = new GetMethod(url);
        try
        {
            int statusCode = new HttpClient().executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK)
            {
                log.error("调用远程图片接口进行图片操作错误，返回码：" + statusCode);
            }
        }
        catch (Exception e)
        {
            log.error("请求远程图片处理接口出现错误", e);
        }
    }
}
