package com.freepaay.systemInit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.freepaay.common.StaticVariables;


public class SystemInit extends HttpServlet
{
    
    private static final long serialVersionUID = 2379885457027637300L;
    
    @Override
    public void init() throws ServletException
    {
        ServletContext application = getServletContext();
        application.setAttribute("fileServer", StaticVariables.IMAG_SERVER_PATH);
        application.setAttribute("fileMapPath", StaticVariables.FILE_MAP_PATH);
        application.setAttribute("contextPath", StaticVariables.SYSTEM_URL);
//        application.setAttribute("contextPath", "http://10.0.1.222:8080/freepaayManage");
        application.setAttribute("resourcesPath", StaticVariables.RESOURCES_SERVER_PATH);
    }
    
    public void contextDestroyed(ServletContextEvent sce)
    {
        System.out.println("这个函数内的代码将在服务器关闭时执行");
    }
    
    /* @Override
     public void onApplicationEvent(ContextRefreshedEvent event)
     {
         if (event.getApplicationContext().getParent() == null)
         {
             XmlWebApplicationContext xwa = (XmlWebApplicationContext)event.getSource();
             System.out.println("这个函数内的代码将在服务器启动时执行");
             try
             {
                
             }
             catch (LanTuException e)
             {
                 e.printStackTrace();
             }
         }
     }*/
}
