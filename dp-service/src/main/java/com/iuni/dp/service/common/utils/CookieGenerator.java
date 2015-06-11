package com.iuni.dp.service.common.utils;

import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.iuni.common.utils.NumBaseConvertor;

/**
 * Cookie Generator
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public class CookieGenerator {

	private static final String UNDERLINE = "_";
//	private static final String UVCookie_DESKey = ParseProperties.getPropertiesValue("uv.cookie.deskey");
	
	/**
	 * 生成数据上报UV标识Cookie
	 * @param String ip
	 * @param Long time
	 * @return String
	 * @throws GeneralSecurityException 
	 */
	public static String genUvCookie(String ip, Long time) 
			throws GeneralSecurityException {
		
		StringBuffer buf = new StringBuffer();
		Random random = new Random();
		
		String cookie = null;
		String[] ipDetail = ip.split("\\.");
		if(null == ipDetail || ipDetail.length <= 0) {
			return cookie;
		}
		
		// 获取FULL IP
		String fullIp = "";
		for(String ipSec : ipDetail) {
			if(ipSec.length() < 3) {
				for(int i = 0; i < 3 - ipSec.length(); i++) {
					fullIp += "0";
				}
			}
			fullIp += ipSec;
		}
		
		// 组合Full IP、current time
		String fullIpB64 = NumBaseConvertor.base10ToX(fullIp, 64);
		buf.append(fullIpB64 + UNDERLINE);
		
		// 组合随机数
		String randomStr = "";
		for(int i = 0; i < 3; i++) {
			randomStr += random.nextInt(10);
		}
		
		String valueB64 = NumBaseConvertor.base10ToX(time + randomStr, 64);
		buf.append(valueB64);
//		String result = DESUtil.getEncryptValueByBase64(UVCookie_DESKey, valueB64);
		
		return buf.toString();
	}
	
	/**
	 * 解析数据上报UV标识Cookie
	 * @param String uvCookie
	 * @return Map<String, Object>
	 * @throws GeneralSecurityException
	 */
	public static Map<String, Object> getDataFromUvCookie(String uvCookie) 
			throws GeneralSecurityException {
		Map<String, Object> data = new HashMap<String, Object>();
		
		String[] cookieDetail = uvCookie.split("\\_");
		if(null != cookieDetail && cookieDetail.length == 2) {
			String fullIp = NumBaseConvertor.xToBase10(cookieDetail[0], 64);
			if(fullIp.length() > 9) {
				String ip1 = fullIp.substring(fullIp.length() - 3, fullIp.length());
				String ip2 = fullIp.substring(fullIp.length() - 6, fullIp.length() - 3);
				String ip3 = fullIp.substring(fullIp.length() - 9, fullIp.length() - 6);
				String ip4 = fullIp.substring(0, fullIp.length() - 9);
				fullIp = ip4 + "." + ip3 + "." + ip2 + "." + ip1;
				data.put("ip", fullIp);
			}
			String remains = NumBaseConvertor.xToBase10(cookieDetail[1], 64);
			if(StringUtils.isNotBlank(remains)) {
				String time = remains.substring(0, remains.length()-3);
				String randomStr = remains.substring(remains.length()-3, remains.length());
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(Long.parseLong(time));
				data.put("time", cal.getTime());
				data.put("randomNumber", randomStr);
			}
			
		}
		
		return data;
	}
	
	/**
	 * 获取[min,max]范围随机数
	 * @param min
	 * @param max
	 * @return int 
	 */
	public static int getRandomInRange(int min, int max) {
		Random random = new Random();
        int val = random.nextInt(max)%(max-min+1) + min;
        
        return val;
	}
	
	public static void main(String[] args) {
		String ip = "18.8.0.103";
		long curTime = System.currentTimeMillis();
		String uvCookie = null;
		
		try {
			uvCookie = genUvCookie(ip, curTime);
			uvCookie = "152cvLn_4UJ5MFGgU";
			Map<String, Object> data = getDataFromUvCookie(uvCookie);
			System.out.println("rptDataCookie: " + uvCookie + " length: " + uvCookie.length());
			System.out.println("data: " + data + " length: " + data.size());
		} catch (GeneralSecurityException e1) {
			e1.printStackTrace();
		}
		
		try {
			Long uvcookieMaxage = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2099-12-31 23:59:59").getTime();
			System.out.println("uvcookieMaxage: " + uvcookieMaxage/1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
}
