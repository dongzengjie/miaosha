package com.dzj.miaosha.emuns;

public enum UserEnum {

	USER_LOGIN_ERROR(-1001,"用户登陆异常"),PASSWORD_ERROR(-10012,"密码错误"),USER_NOT_EXIST(-1101,"用户不存在");
	private int state;
	private String StateInfo;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return StateInfo;
	}
	public void setStateInfo(String stateInfo) {
		StateInfo = stateInfo;
	}
	
	private UserEnum(int state,String stateInfo) {
		this.state=state;
		this.StateInfo=stateInfo;
	}
	
	public static UserEnum stateof(int index) {
		for (UserEnum userenum : values()) {
			if(userenum.getState()==index) {
				return userenum;
			}
		}
		return null;
	}
}
