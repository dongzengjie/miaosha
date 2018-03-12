package com.dzj.miaosha.result;

public class CodeMsg {

	private int code;
	private String msg;
	
	//通用异常
	public static CodeMsg SUCCESS =new CodeMsg(0,"success");
	public static CodeMsg SERVICE_ERROR =new CodeMsg(500100,"服务端异常");
	
	
	//登陆模块异常

	
	//订单模块异常
	private CodeMsg(int i, String string) {
	
		this.code =i;
		this.msg=string;
	}
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
}
