package com.freepaay.manage.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

public class MessageConvertForSys implements MessageConverter {

	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		System.out.println("sendMessage:" + object.toString());
		ObjectMessage objectMessage = session.createObjectMessage();
		objectMessage.setStringProperty("key_freepaayManage", object.toString());
		return objectMessage;
	}

	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		ObjectMessage objectMessage = (ObjectMessage) message;
		return objectMessage.getObjectProperty("key_freepaayManage");
	}

}