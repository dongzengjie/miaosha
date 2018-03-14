package com.dzj.miaosha.emuns;

public enum GlobalEnums {
	
	
	Server_ERROR(-1111,"服务端异常");
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
	
	private GlobalEnums(int state,String stateInfo) {
		this.state=state;
		this.StateInfo=stateInfo;
	}
	
	public static GlobalEnums stateof(int index) {
		for (GlobalEnums globalenum : values()) {
			if(globalenum.getState()==index) {
				return globalenum;
			}
		}
		return null;
	}
}
