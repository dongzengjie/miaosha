package com.dzj.miaosha.entity;

import java.util.Date;

public class User {
	private Long userId;
	private String userName;
	private String password;
	private String salt;
	private String head;
	private Date regsiterTime;
	private Date lastLoginTime;
	private int loginCount;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public Date getRegsiterTime() {
		return regsiterTime;
	}
	public void setRegsiterTime(Date regsiterTime) {
		this.regsiterTime = regsiterTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", salt=" + salt
				+ ", head=" + head + ", regsiterTime=" + regsiterTime + ", lastLoginTime=" + lastLoginTime
				+ ", loginCount=" + loginCount + "]";
	}
	
	
}
