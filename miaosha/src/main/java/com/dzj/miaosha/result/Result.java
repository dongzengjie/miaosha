package com.dzj.miaosha.result;

public class Result<T> {

	private int code;
	private T data;
	private String msg;
	
	private  Result(T data) {
		this.code=0;
		this.msg="success";
		this.data=data;
		
	}
	
	private  Result(CodeMsg msg) {
		if(msg == null) {
			return;
		}
		this.code=msg.getCode();
		this.msg=msg.getMsg();
		
		
	}
	public int getCode() {
		return code;
	}
	public T getData() {
		return data;
	}
	public String getMsg() {
		return msg;
	}
	
	public static <T> Result<T> success(T data){
		return new Result(data);
		
	}
	
	public static <T> Result<T> error(CodeMsg  msg){
		
		return new Result(msg);
	}
 }
