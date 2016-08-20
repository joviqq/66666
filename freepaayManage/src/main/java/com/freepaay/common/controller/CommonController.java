package com.freepaay.common.controller;

import org.springframework.stereotype.Controller;

/**
 * 
 * 公共controller
 * 
 * @author 福福
 * @data 2016年6月5日
 */
@Controller
public class CommonController {

	// /**
	// * <pre>
	// * 生成随机验证码
	// * 调用工具类RandomValidateCode生成jsp验证码并将验证码放在session中validateCode
	// */
	// @RequestMapping(value = "/randomValidateCode")
	// public void getRandomValidateCode(HttpServletRequest request,
	// HttpServletResponse response)
	// {
	// RandomValidateCode validateCode = new RandomValidateCode();
	// validateCode.getRandcode(request, response);
	// }
	//
	// /**
	// *
	// * <pre>
	// * 验证码校验
	// * @param session
	// * @param validateCode
	// * @return
	// * @throws FreepaayException [参数说明]
	// * @author 福福 2016年6月1日 下午2:39:44
	// */
	// @RequestMapping(value = "/verifyCode")
	// @ResponseBody
	// public String verifyCode( HttpSession session, String validateCode)
	// throws FreepaayException
	// {
	// JSONObject json = new JSONObject();
	// if (session == null)
	// throw new FreepaayException("验证验证码失败");
	// String vaildateCodeInSession =
	// (String)session.getAttribute("validateCode");
	// if (vaildateCodeInSession != null &&
	// vaildateCodeInSession.equalsIgnoreCase(validateCode))
	// {
	// json.put("ok", "");
	// }else{
	// json.put("error", "");
	// }
	// return json.toString();
	//
	// }

}
