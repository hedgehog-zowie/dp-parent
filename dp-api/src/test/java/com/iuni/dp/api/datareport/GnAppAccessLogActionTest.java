package com.iuni.dp.api.datareport;

import java.security.GeneralSecurityException;
import java.sql.Timestamp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iuni.common.utils.AESUtil;
import com.iuni.dp.api.common.utils.HttpUtil;
import com.iuni.dp.api.datareport.constants.ReportdataContant;
import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.persist.datareport.model.GnAppAccessLog;

/**
 * Test GnAppAccessLogAction
 * @author Kenneth.Cai@iuni.com
 * @version dp-api-V1.1.2
 */
public class GnAppAccessLogActionTest {

	private static final String GNAPP_AES_KEY = ParseProperties
			.getPropertiesValue(ReportdataContant.GNAPP_AES_KEY);
	
	public static void main(String[] args) {
		GnAppAccessLogActionTest test = new GnAppAccessLogActionTest();
		
		// fetch Data By Get
//		test.fetchDataByGet();
		
		// fetch Data By Post
		test.fetchDataByPost();
	}
	
	/**
	 * fetch Data By Get
	 * @return String
	 * @throws GeneralSecurityException 
	 */
	public String fetchDataByGet() {
		String result = null;
		
		String encodeImei = "";
		String encodeMobileNum = "";
		try {
			encodeImei = AESUtil.getEncryptValueByBase64(GNAPP_AES_KEY, "1234567890");
			encodeMobileNum = AESUtil.getEncryptValueByBase64(GNAPP_AES_KEY, "15912425332");
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		
		String uri = "http://dp.ck.com:8080/dp/sendAppAccessLog";
		GnAppAccessLog gnAppAccessLog = new GnAppAccessLog("玩机圈APP", "应用汇",
				"apk20130903", encodeImei, "afs31232345432", new Timestamp(
						System.currentTimeMillis()), new Timestamp(
						System.currentTimeMillis()), 1, "HTC", encodeMobileNum,
				1920, 1080, "3G", "14.197.239.255", "中国", "深圳", "福田区",
				new Timestamp(System.currentTimeMillis()), new Timestamp(
						System.currentTimeMillis()));
		String data = JSON.toJSONString(gnAppAccessLog,SerializerFeature.WriteMapNullValue);
		String reqUrl = "?data=" + data;
		uri += reqUrl;
		
		result = HttpUtil.get4Data(uri, "UTF-8");
		System.out.println(result);
		
		return result;
	}
	
	/**
	 * fetch Data By Post
	 * @return String
	 * @throws GeneralSecurityException 
	 */
	public String fetchDataByPost() {
		String result = null;
		
		String encodeImei = "";
		String encodeMobileNum = "";
		try {
			encodeImei = AESUtil.getEncryptValueByBase64(GNAPP_AES_KEY, "1234567890");
			encodeMobileNum = AESUtil.getEncryptValueByBase64(GNAPP_AES_KEY, "15912425332");
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		
		String uri = "http://dp.ck.com:8080/dp/sendAppAccessLog";
		GnAppAccessLog gnAppAccessLog = new GnAppAccessLog("玩机圈APP", "应用汇",
				"apk20130903", encodeImei, "afs31232345432",
				new Timestamp(System.currentTimeMillis()), new Timestamp(
						System.currentTimeMillis()), 1, "HTC", encodeMobileNum,
				1920, 1080, "3G", "14.197.239.255", "中国", "深圳", "福田区",
				new Timestamp(System.currentTimeMillis()), new Timestamp(
						System.currentTimeMillis()));
		String data = JSON.toJSONString(gnAppAccessLog,SerializerFeature.WriteMapNullValue);
		
		result = HttpUtil.post4Data(data, uri, "UTF-8", "application/json; charset=UTF-8");
		System.out.println(result);
		
		return result;
	}
	
}
