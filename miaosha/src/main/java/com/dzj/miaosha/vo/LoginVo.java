package com.dzj.miaosha.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 校验用户登陆
 * @author DZJ
 *
 */
public class LoginVo {

	@NotNull
	private String mobile;
	
	@NotNull
	@Length(min=11)
	private String password;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
