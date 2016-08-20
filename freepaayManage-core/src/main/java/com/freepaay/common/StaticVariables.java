
package com.freepaay.common;
/**
 * 
 * <静态描述>
 * @author  福福
 * @data  2016年6月5日
 */
public class StaticVariables
{

    /**
     * REDIS缓存时间
     * 单位是秒, 值是60
     */
    public static int REDIS_SECONDS = 60;
    
    /**
     * 数据库删除状态: 1
     */
    public static String STATUS_DELETE = "1";
    
    /**
     * 数据库非删除状态: 0
     */
    public static String STATUS_NORMAL = "0";
    
//    public static String SYSTEM_URL = "http://10.0.1.222:8080/freepaayManage"; //测试环境，线上环境用
    
    public static String SYSTEM_URL = "http://localhost:8080/freepaayManage"; //开发环境用
    
    /**
     * 图片服务器地址
     */
//    public static final String IMAG_SERVER_PATH = "";
    public static final String IMAG_SERVER_PATH = "http://localhost:8080/freepaayManage";
    
    /**
     * resources文件服务器地址
     */
//    public static final String RESOURCES_SERVER_PATH = "http://";
    public static final String RESOURCES_SERVER_PATH = "http://localhost:8080/freepaayManage";
    
    /**
     * 上传图片的本地映射服务器地址（虚拟盘符）
     */
    //public static final String FILE_MAP_PATH = "/usr/ltFiles/"; //测试环境，线上环境用
    public static final String FILE_MAP_PATH = System.getProperty("file.separator") + "home" + System.getProperty("file.separator") + "ltFiles";
    
    /**
     * session中存放userId对应的key
     */
    public static String SESSION_USERID = "userId";
    
    /**
     * session中存放userId对应的username
     */
    public static String SESSION_USERNAME = "userName";
    
    /**
     * session中存放的用户
     */
    public static String SESSION_USER = "user";
    
    /**
     * 默认分页大小
     */
    public static int DEFAULT_PAGESIZE = 10;
    
    
    /**
     * 大区划分定义
     */
    /**
     * 华北地区 142100000
     */
    public static String DQ_HUABEI = "1421";
    
    /**
     * 东北地区 142200000
     */
    public static String DQ_DONGBEI = "1422";
    
    /**
     * 华东地区 142300000
     */
    public static String DQ_HUADONG = "1423";
    
    /**
     * 中南地区 142400000
     */
    public static String DQ_ZHONGNAN = "1424";
    
    /**
     * 西南地区 142500000
     */
    public static String DQ_XINAN = "1425";
    
    /**
     * 西北地区 142600000
     */
    public static String DQ_XIBEI = "1426";
    
}
