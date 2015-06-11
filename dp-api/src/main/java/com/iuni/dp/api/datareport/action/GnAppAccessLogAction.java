package com.iuni.dp.api.datareport.action;

import java.io.BufferedReader;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iuni.common.utils.AESUtil;
import com.iuni.dp.api.common.action.BaseAction;
import com.iuni.dp.api.datareport.constants.ReportdataContant;
import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.persist.datareport.model.GnAppAccessLog;
import com.iuni.dp.service.common.bean.BaseResult;

/**
 * 玩机圈客户端登入登出记录日志Action
 * @author CaiKe
 * @version dp-mq-1.0.0
 */
@Controller("gnAppAccessLogAction")
@Scope("prototype")
public class GnAppAccessLogAction extends BaseAction {

	private static final long serialVersionUID = 4934410537416035391L;

	private static final String GNAPP_AES_KEY = ParseProperties
			.getPropertiesValue(ReportdataContant.GNAPP_AES_KEY);
	
	private static final String gnAppRptDataLogger = ParseProperties
			.getPropertiesValue(ReportdataContant.GNAPP_REPORTDATA_LOGGER);
	
	private final Logger logger = LoggerFactory.getLogger(GnAppAccessLogAction.class);
	
	private final Logger logger_dpAppData = LoggerFactory.getLogger(gnAppRptDataLogger);
	
	/**
	 * 发送玩机圈客户端登入登出记录日志至AMQ Queue
	 */
	public void sendGnAppAccessLog() {
		BaseResult result = new BaseResult();
		
		try {
			String data = null;
			String contentType = request.getContentType();
			
			if(StringUtils.isBlank(contentType)) {
				result.setState(BaseResult.FAIL);
				printJson2(result, false, false);
				return;
			}
			
			if(contentType.startsWith("application/x-www-form-urlencoded")) {
				data = request.getParameter("data");
			} else if(contentType.startsWith("application/json")) {
				BufferedReader reader = request.getReader();
				String line = null;
				StringBuffer jsonBuf = new StringBuffer();
				while((line = reader.readLine()) != null) {  
					jsonBuf.append(line);  
			    }
				data = jsonBuf.toString();
			}
			
			if(StringUtils.isBlank(data)) {
				result.setState(BaseResult.FAIL);
				printJson2(result, false, false);
				return;
			}
			// 反序列化请求参数
			GnAppAccessLog gnAppAccessLog = JSON.parseObject(data, GnAppAccessLog.class);
//			String appName = gnAppAccessLog.getAppName();
//			String channelName = gnAppAccessLog.getChannelName();
//			String apkVersion = gnAppAccessLog.getApkVersion();
			String imei = gnAppAccessLog.getImei();
//			Timestamp startupTime = gnAppAccessLog.getStartupTime();
//			Integer status = gnAppAccessLog.getStatus();
			String mobileNumber = gnAppAccessLog.getMobileNumber();
			
			// 校验客户端APP上报数据
//			if(StringUtils.isBlank(appName) || StringUtils.isBlank(channelName) 
//					|| StringUtils.isBlank(apkVersion) || StringUtils.isBlank(imei) 
//					|| null == startupTime || null == status) {
//				result.setState(BaseResult.FAIL);
//				printJson2(result, false, false);
//				return;
//			}
			
			// 解密IMEI
			if(StringUtils.isNotBlank(imei)) {
				String decryptedImei = AESUtil.getDecryptValueByBase64(GNAPP_AES_KEY, imei);
				gnAppAccessLog.setImei(decryptedImei);
			}
			
			// 设置上报时间
			Timestamp reportTime = new Timestamp(System.currentTimeMillis());
			gnAppAccessLog.setReportTime(reportTime);
			
			// 解密Mobile Number
			if(StringUtils.isNotBlank(mobileNumber)) {
				String decryptedMobileNo = AESUtil.getDecryptValueByBase64(GNAPP_AES_KEY, mobileNumber);
				gnAppAccessLog.setMobileNumber(decryptedMobileNo);
			}
			
			/**
			 * DP-API项目数据上报实现方案(LogBack写文件入库方式)
			 */
			String gnAppAccessLogStr = JSON.toJSONString(gnAppAccessLog, SerializerFeature.WriteMapNullValue);
			logger_dpAppData.info(gnAppAccessLogStr);
			
			logger.debug("GnAppAccessLogAction.sendGnAppAccessLog send msg to amq queue, gnAppAccessLog={}"
					, new Object[]{gnAppAccessLog});
			result.setState(BaseResult.SUCCESS);
			
		} catch (Exception e) {
			result.setState(BaseResult.FAIL);
			logger.error("GnAppAccessLogAction.sendGnAppAccessLog found Exception", e);
		}
		
		printJson2(result, false, false);
		
	}
	
}
