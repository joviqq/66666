
package com.freepaay.common;

/**
 * <获取统一主键的方法入口>
 * <主键=时间戳+随机数>
 * 根据需要使用
 * @author  福福
 */
public class IdUtils 
{
    public static IdUtils  instance = null;

    private static final long ONE_STEP = 100;
    private static long lastTime = System.currentTimeMillis();
    private static short count = 0;

    /**
    * 获取单例对象
   * @return
    */
    public synchronized static IdUtils getInstanse(){
      if(instance == null){
          instance = new IdUtils();
      }
      return instance;
    }

    private IdUtils(){
    }
    /**
    * 根据对象获取表主键
    * @param clazz
    * @return
    */
    public synchronized String getUID()
    {
        try
        {
            if (count == ONE_STEP)
            {
                boolean done = false;
                while (!done)
                {
                    long now = System.currentTimeMillis();
                    if (now == lastTime)
                    {
                        try
                        {
                            Thread.sleep(1);
                        }
                        catch (java.lang.InterruptedException e)
                        {
                        }
                        continue;
                    }
                    else
                    {
                        lastTime = now;
                        count = 0;
                        done = true;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String result = lastTime + "" + (count++);
        return result;
    }
    
    public static void main(String args[])
    {
        System.out.println(IdUtils.getInstanse().getUID());
    }
}

