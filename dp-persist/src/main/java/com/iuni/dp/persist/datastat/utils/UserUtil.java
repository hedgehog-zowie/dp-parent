package com.iuni.dp.persist.datastat.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User Util
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.5
 */
public class UserUtil {

	public static final String phone_regExp = "^0{0,1}(13\\d|147|15[0-3]|15[5-9]|18[0|1|2|3|5-9])\\d{8}$";
	public static final String mail_regExp = "^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+";
	public static final String username_regExp = "^([\u4E00-\u9FA5]|[0-9a-zA-Z_])+$";
//	public static final String NUMBER_REGEXP = "[0-9]+";
	public static final String NUMBER_REGEXP = "^1\\d{10}$";
	
	public static boolean isPhoneNum(String num) {
		Pattern p = Pattern.compile(phone_regExp);
		Matcher m = p.matcher(num);
		return m.find();
	}

	public static boolean isMail(String str) {
		Pattern p = Pattern.compile(mail_regExp);
		Matcher m = p.matcher(str);
		return m.find();
	}

	public static boolean isNum(String str) {
		if (str == null)
			return false;
		boolean result = str.matches(NUMBER_REGEXP);
		return result;
	}

	/**
	 * 判定是否是一个正确的用户名，只能由数字，字母，及下划线组成
	 * 
	 * @param userName
	 *            用户名称
	 * @return
	 */
	public static boolean isTrueUserName(String userName) {
		Pattern p = Pattern.compile(username_regExp);
		Matcher m = p.matcher(userName);
		return m.find();
	}

	public static void main(String[] st) {
//		System.out.println(isNum("11234234212"));
		System.out.println(isPhoneNum("565989898"));
//		 System.out.println(isMail("dd@163.com"));
		// String username="a_s_";
		// if
		// (username.indexOf("_")==0||username.lastIndexOf("_")==(username.length()-1)){
		// System.out.println("kkk");
		// }
	}
	
}
