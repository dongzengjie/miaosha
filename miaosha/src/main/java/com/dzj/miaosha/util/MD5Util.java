package com.dzj.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

	private MD5Util() {
		
	}
	
	public static final String salt = "1a2b3c4d";
	
	
	public static String md5(String src) {
		return DigestUtils.md5Hex(src).toString();			
	}
	
	/**
	 * 将用户输入的密码转成md5加密
	 * @param inputString
	 * @return
	 */
	public static String inputPassToFormPass(String inputString) {
		String formpass=""+salt.charAt(0)+salt.charAt(2)+inputString+salt.charAt(5)+salt.charAt(4);
		return md5(formpass);
		
	}
	
	public static String formPassToDBPass(String formpass,String salt ) {
		String str=""+salt.charAt(0)+salt.charAt(2)+formpass+salt.charAt(5)+salt.charAt(4);
		return md5(str);
	}
	
	
	public static String inputPassToDbPass(String inputPass, String saltDB) {
		String formPass = inputPassToFormPass(inputPass);
		String dbPass = formPassToDBPass(formPass, saltDB);
		return dbPass;
	}
	public static void main(String[] args) {
		System.out.println(inputPassToFormPass("123456"));//d3b1294a61a07da9b49b6e22b2cbd7f9
		System.out.println(formPassToDBPass(inputPassToFormPass("123456"), "1a2b3c4d"));
		System.out.println(inputPassToDbPass("123456", "1a2b3c4d"));//b7797cce01b4b131b433b6acf4add449
	}
}
