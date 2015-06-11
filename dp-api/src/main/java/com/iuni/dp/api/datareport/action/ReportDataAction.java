package com.iuni.dp.api.datareport.action;

import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iuni.common.utils.AESUtil;
//import com.gionee.dp.api.datareport.facade.ReportDataByMonFacade;
import com.iuni.dp.api.common.action.BaseAction;
import com.iuni.dp.api.common.iplibrary.purelibrary.IPSeeker;
import com.iuni.dp.api.datareport.constants.ReportdataContant;
//import com.gionee.dp.service.datareport.constants.ReportDataType;
import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.persist.datareport.model.ReportDataByMon;
import com.iuni.dp.service.common.bean.BaseResult;
import com.iuni.dp.service.common.utils.CookieGenerator;

/**
 * 前端通用数据上报HTTP接口
 * @author CaiKe
 * @since dp-api-V1.0.0
 * @version dp-api-V1.0.2
 */
@Controller("reportDataAction")
@Scope("prototype")
public class ReportDataAction extends BaseAction {

	private static final long serialVersionUID = 5191727036753834692L;

	private static final String REPORT_TYPE = "type";
	private static final String SOURCE_ID = "id";
	private static final String USER_ID = "uid";
	private static final String SESSION_ID = "sid";
	private static final String REPORT_DATA = "data";
	private static final String UVCookieName = "vk";
	private static final String APP_NAME = "appname";
	private static final String APP_CHANNEL = "appchannel";
	private static final String APP_VERSION = "appversion";
	private static final String APP_IMEI = "appimei";
	private static final String APP_MODEL = "appmodel";
	private static final String APP_IP = "appip";
	
	private static final String GNAPP_AES_KEY = ParseProperties
			.getPropertiesValue(ReportdataContant.GNAPP_AES_KEY);
	private static final String cmRptDataLogger = ParseProperties
			.getPropertiesValue(ReportdataContant.COMMON_REPORTDATA_LOGGER);
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Logger logger_dpCmData = LoggerFactory.getLogger(cmRptDataLogger);
	
	// 根据IP地址定位区域、位置或服务的工具类实例
	private IPSeeker ipSeeker = IPSeeker.getInstance();
	
//	@Autowired
//	private ReportDataByMonFacade<ReportDataByMon> reportDataByMonFacade;
	
	/**
	 * ReportData类型上报数据单条数据上报
	 */
	public void reportData() {
		long stime = System.currentTimeMillis();
		
		// 获取上报日期相关信息
		Calendar cal = Calendar.getInstance();
		Date curDate = cal.getTime();
		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		
		BaseResult result = new BaseResult();
		
		String rptType = request.getParameter(REPORT_TYPE);
		rptType = subStringByChar(rptType, 80);
		
		String sourceId = request.getParameter(SOURCE_ID);
		sourceId = subStringByChar(sourceId, 650);
		
		String userId = request.getParameter(USER_ID);
		userId = subStringByChar(userId, 80);
		
		String sessionId = request.getParameter(SESSION_ID);
		sessionId = subStringByChar(sessionId, 80);
		
		String rptContent = request.getParameter(REPORT_DATA);
		rptContent = subStringByChar(rptContent, 650);
		
		String browserInfo = request.getHeader("user-agent");
		
		String urlDomain = request.getServerName();
		urlDomain = subStringByChar(urlDomain, 80);
		
		// 获取APP相关上报信息
		String appName = request.getParameter(APP_NAME);
		appName = subStringByChar(appName, 325);
		
		String appChannel = request.getParameter(APP_CHANNEL);
		appChannel = subStringByChar(appChannel, 325);
		
		String appVersion = request.getParameter(APP_VERSION);
		appVersion = subStringByChar(appVersion, 325);
		
		String appImei = request.getParameter(APP_IMEI);
//		appImei = subStringByChar(appImei, 325);
		
		String appModel = request.getParameter(APP_MODEL);
		appModel = subStringByChar(appModel, 325);
		
		String appIp = request.getParameter(APP_IP);
		appIp = subStringByChar(appIp, 325);
		
		// 获取IP信息
		String sourceIp = getIp();
		sourceIp = subStringByChar(sourceIp, 80);
		
		String ipArea = "";
		String ipLocation = "";
		
		// 获取IP位置信息
		try {
			ipArea = ipSeeker.getArea(sourceIp);
			ipLocation = ipSeeker.getLocation(sourceIp);
		} catch (Exception e) {
			logger.error("ReportDataAction.reportData get ipArea and ipLocation found error.");
		}
		
		// 用户请求UV Cookie处理
		String uvCookieVal = uvCookieProcessor(sourceIp);
		uvCookieVal = subStringByChar(uvCookieVal, 80);
		
		try {
			// 解密IMEI
			String decryptedImei = "";
			if(StringUtils.isNotBlank(appImei)) {
				decryptedImei = AESUtil.getDecryptValueByBase64(GNAPP_AES_KEY, appImei);
			}
			
			ReportDataByMon rptData = new ReportDataByMon(urlDomain, sourceId,
					userId, sessionId, sourceIp, rptType, rptContent, browserInfo,
					uvCookieVal, ipArea, ipLocation, appName, appChannel,
					appVersion, decryptedImei, appModel, appIp, curDate, month);
			/**
			 * DRS项目上报数据实现方案(阻塞队列缓存写文件入库方式)
			 * 已是历史废弃实现方式
			 */
//			result = reportDataByMonFacade.reportData(ReportDataType.CommonRptdata, rptData);
			
			/**
			 * DP-API项目数据上报实现方案(LogBack写文件入库方式)
			 */
			String rptDataString = JSON.toJSONString(rptData,
					SerializerFeature.WriteDateUseDateFormat,
					SerializerFeature.WriteMapNullValue);
			logger_dpCmData.info(rptDataString);
			
			result.setState(BaseResult.SUCCESS);
			
			// Action返回JSON数据
			printJson2(result, false, false);
			
			logger.debug("ReportDataAction.reportData() success: costTime={}ms" 
					,new Object[] { System.currentTimeMillis() - stime });
			
		} catch (Exception e) {
			result.setState(BaseResult.FAIL);
			// Action返回JSON数据
			printJson2(result, false, false);
			
			logger.debug("ReportDataAction.reportData() failed: costTime={}ms" 
					,new Object[] { System.currentTimeMillis() - stime });
		}
		
	}
	
	/**
	 * 按字符数截取字符串
	 */
	private String subStringByChar(String value, int subCount) {
		String result = null;
		
		if(null == value) {
			return value;
		}
		
		if(value.length() > subCount) {
			result = value.substring(0, subCount);
		} else {
			result = value;
		}
		
		return result;
	}
	
	/**
	 * 用户请求UV Cookie处理
	 * @param sourceIp
	 */
	private String uvCookieProcessor(String sourceIp) {
		String uvCookieVal = null;
		
		Boolean isHaveUvCookie = false;
		
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				if(UVCookieName.equalsIgnoreCase(cookieName)) {
					isHaveUvCookie = true;
					uvCookieVal = cookie.getValue();
				}
			}
		}
		
		if(!isHaveUvCookie) {
			// 获取UV Cookie Value
			uvCookieVal = getUvCookieVal(sourceIp);
			
			// 新增UV Cookie
			addUvCookie(uvCookieVal);
		} 
		
		return uvCookieVal;
	}
	
	/**
	 * 新增UV Cookie
	 * @param sourceIp
	 */
	private Boolean addUvCookie(String uvCookieVal) {
		Boolean status = false;
		
		if(StringUtils.isNotBlank(uvCookieVal)) {
			String uvCookieName = "vk";
			String serverName = request.getServerName();
			String[] serverNameDetail = serverName.split("\\.");
			String domain = serverName;
			if(null != serverNameDetail && serverNameDetail.length >= 2) {
				domain = serverNameDetail[serverNameDetail.length -2] + "." + serverNameDetail[serverNameDetail.length - 1];
			}
			
			Cookie uvCookie = new Cookie(uvCookieName, uvCookieVal);
			uvCookie.setDomain(domain);
			uvCookie.setPath("/");
			uvCookie.setMaxAge(Integer.MAX_VALUE);
			
			response.addCookie(uvCookie);
			status = true;
		}
		
		return status;
	}
	
	/**
	 * 获取UV Cookie Value
	 * @param sourceIp
	 * @return String
	 */
	private String getUvCookieVal(String sourceIp) {
		String uvCookieVal = null;
		long time = System.currentTimeMillis();
		
		try {
			uvCookieVal = CookieGenerator.genUvCookie(sourceIp, time);
		} catch (GeneralSecurityException e) {
			logger.error("ReportDataAction.getUvCookieVal found GeneralSecurityException");
		}
		
		return uvCookieVal;
	}
	
}
