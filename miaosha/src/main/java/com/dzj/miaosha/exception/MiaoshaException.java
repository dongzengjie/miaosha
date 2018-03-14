package com.dzj.miaosha.exception;

public class MiaoshaException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	private int code;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public MiaoshaException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public MiaoshaException(String message) {
		super(message);
	
	}
	
	public MiaoshaException(String message,int code) {
		super(message);
		this.code=code;
	
	}
	

}
