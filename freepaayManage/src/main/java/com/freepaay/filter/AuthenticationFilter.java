package com.freepaay.filter;

import java.util.Collection; 
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.freepaay.common.AuthenticationToken;

/**
 * Filter - 权限认证
 * 封装需要的token，后期根据需要修改使用
 * 福福
 */
public class AuthenticationFilter extends FormAuthenticationFilter {

	/** 默认"验证ID"参数名称 */
	private static final String DEFAULT_CAPTCHA_ID_PARAM = "captchaId";

	/** 默认"验证码"参数名称 */
	private static final String DEFAULT_CAPTCHA_PARAM = "captcha";


	/** "验证ID"参数名称 */
	private String captchaIdParam = DEFAULT_CAPTCHA_ID_PARAM;

	/** "验证码"参数名称 */
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	/**
	 * 创建token
	 */
	@Override
	protected org.apache.shiro.authc.AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
		String username = getUsername(servletRequest);
		String password = getPassword(servletRequest);
		String captchaId = getCaptchaId(servletRequest);
		String captcha = getCaptcha(servletRequest);
		boolean rememberMe = isRememberMe(servletRequest);
		String host = getHost(servletRequest);
		String validateCode = (String)((HttpServletRequest) servletRequest).getSession().getAttribute("validateCode");;
		return new AuthenticationToken( username,  password,
				 captchaId,  captcha,  validateCode,
				 rememberMe,  host) ;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
			response.addHeader("loginStatus", "accessDenied");
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}
		return super.onAccessDenied(request, response);
	}

	@Override
	protected boolean onLoginSuccess(org.apache.shiro.authc.AuthenticationToken token, Subject subject, ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		Session session = subject.getSession();
		Map<Object, Object> attributes = new HashMap<Object, Object>();
		Collection<Object> keys = session.getAttributeKeys();
		for (Object key : keys) {
			attributes.put(key, session.getAttribute(key));
		}
		session.stop();
		session = subject.getSession();
		for (Entry<Object, Object> entry : attributes.entrySet()) {
			session.setAttribute(entry.getKey(), entry.getValue());
		}
		return super.onLoginSuccess(token, subject, servletRequest, servletResponse);
	}

	/**
	 *获取密码
	 * @param servletRequest
	 * @return
	 */
	@Override
	protected String getPassword(ServletRequest servletRequest) {
		String password = (String)((HttpServletRequest) servletRequest).getAttribute("password");
		return password;
	}

	/**
	 * 获取验证ID
	 * 
	 * @param servletRequest
	 *            ServletRequest
	 * @return 验证ID
	 */
	protected String getCaptchaId(ServletRequest servletRequest) {
		String captchaId = WebUtils.getCleanParam(servletRequest, captchaIdParam);
		if (captchaId == null) {
			captchaId = ((HttpServletRequest) servletRequest).getSession().getId();
		}
		return captchaId;
	}

	/**
	 * 获取请求验证码
	 */
	protected String getCaptcha(ServletRequest servletRequest) {
		return (String)((HttpServletRequest) servletRequest).getAttribute("validateCode");
	}

	
	/**
	 * 获取"验证ID"参数名称
	 * 
	 * @return "验证ID"参数名称
	 */
	public String getCaptchaIdParam() {
		return captchaIdParam;
	}

	/**
	 * 设置"验证ID"参数名称
	 * 
	 * @param captchaIdParam
	 *            "验证ID"参数名称
	 */
	public void setCaptchaIdParam(String captchaIdParam) {
		this.captchaIdParam = captchaIdParam;
	}

	/**
	 * 获取"验证码"参数名称
	 * 
	 * @return "验证码"参数名称
	 */
	public String getCaptchaParam() {
		return captchaParam;
	}

	/**
	 * 设置"验证码"参数名称
	 * 
	 * @param captchaParam
	 *            "验证码"参数名称
	 */
	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

}