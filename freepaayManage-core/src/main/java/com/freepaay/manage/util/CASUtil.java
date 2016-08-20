package com.freepaay.manage.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.freepaay.exception.FreepaayException;
import com.freepaay.exception.FreepaayException;

@Controller
public class CASUtil {
	
	public static String getCurrentUserName(HttpServletRequest request,HttpSession session) throws FreepaayException {
		String cusUser=null;
		if(null==request.getRemoteUser()){
			session.setAttribute("userName",null);
			return null;
		}
		if(null==session.getAttribute("userName")){
			if(null==request.getRemoteUser()){
				return null;
			}else{
				cusUser=request.getRemoteUser();
				session.setAttribute("userName",cusUser);
				return cusUser;
			}
		}else{
			return (String)session.getAttribute("userName");
		}
	}
	
}
