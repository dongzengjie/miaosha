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
	
	private  Result(String msg,int code) {
		if(msg == null) {
			return;
		}
		this.code=code;
		this.msg=msg;
		
		
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
	
	public static <T> Result<T> error(String msg,int code){
		
		return new Result(msg,code);
	}
 }
