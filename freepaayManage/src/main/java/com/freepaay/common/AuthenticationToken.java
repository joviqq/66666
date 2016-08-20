package com.freepaay.common;

import org.apache.shiro.authc.UsernamePasswordToken;  

/**
 * 自定义登录令牌
 * 根据后期需要修改使用
 */
public class AuthenticationToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 5898441540965086534L;

	/** 验证码ID */
	private String captchaId;

	/** 验证码 */
	private String captcha;

	/** session中的验证码 */
	private String validateCode;

	/**
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param captchaId
	 *            验证码ID
	 * @param captcha
	 *            验证码
	 * @param rememberMe
	 *            记住我
	 * @param host
	 *            IP
	 */
	public AuthenticationToken(String username, String password,
			String captchaId, String captcha, String validateCode,
			boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
		this.captchaId = captchaId;
		this.captcha = captcha;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	/**
	 * 获取验证码ID
	 * 
	 * @return 验证码ID
	 */
	public String getCaptchaId() {
		return captchaId;
	}

	/**
	 * 设置验证码ID
	 * 
	 * @param captchaId
	 *            验证码ID
	 */
	public void setCaptchaId(String captchaId) {
		this.captchaId = captchaId;
	}

	/**
	 * 获取验证码
	 * 
	 * @return 验证码
	 */
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * 设置验证码
	 * 
	 * @param captcha
	 *            验证码
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}