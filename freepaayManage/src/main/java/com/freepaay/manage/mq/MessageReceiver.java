package com.freepaay.manage.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.stereotype.Controller;

import net.sf.json.JSONObject;

@Controller
public class MessageReceiver implements MessageListener {

	public void onMessage(Message m) {
		ObjectMessage om = (ObjectMessage) m;
		try {
			String key_freepaayManage = om.getStringProperty("key_freepaayManage");
			JSONObject object1 = JSONObject.fromObject(key_freepaayManage);
			String objectName = (String)object1.get("objectName");
			if(objectName.equals("TbConOrdVo")){
				JSONObject object2 = (JSONObject) object1.get("object");
			}
			System.out.println("==============MQ Write to Database success============");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}