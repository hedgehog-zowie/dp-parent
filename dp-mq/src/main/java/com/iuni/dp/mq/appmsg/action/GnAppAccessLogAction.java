package com.iuni.dp.mq.appmsg.action;

import java.io.BufferedReader;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.iuni.common.utils.AESUtil;
import com.iuni.dp.mq.appmsg.activemq.producer.GnAppAccessLogProducer;
import com.iuni.dp.mq.appmsg.constants.GnAppReportConstant;
import com.iuni.dp.mq.common.action.BaseAction;
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

	private static final Logger logger = LoggerFactory.getLogger(GnAppAccessLogAction.class);
	
	private static final String WJQAPP_AES_KEY = ParseProperties.getPropertiesValue(GnAppReportConstant.WJQAPP_AES_KEY);
	
	@Autowired
	private GnAppAccessLogProducer gnAppAccessLogProducer;
	
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
			String appName = gnAppAccessLog.getAppName();
			String channelName = gnAppAccessLog.getChannelName();
			String apkVersion = gnAppAccessLog.getApkVersion();
			String imei = gnAppAccessLog.getImei();
			Timestamp startupTime = gnAppAccessLog.getStartupTime();
			Integer status = gnAppAccessLog.getStatus();
			
			// 校验客户端APP上报数据
			if(StringUtils.isBlank(appName) || StringUtils.isBlank(channelName) 
					|| StringUtils.isBlank(apkVersion) || StringUtils.isBlank(imei) 
					|| null == startupTime || null == status) {
				result.setState(BaseResult.FAIL);
				printJson2(result, false, false);
				return;
			}
			
			// 解密IMEI
			String decryptedImei = AESUtil.getDecryptValueByBase64(WJQAPP_AES_KEY, imei);
			gnAppAccessLog.setImei(decryptedImei);
			
			// 设置上报时间
			Timestamp reportTime = new Timestamp(System.currentTimeMillis());
			gnAppAccessLog.setReportTime(reportTime);
			
			gnAppAccessLogProducer.send(gnAppAccessLog);
			logger.info("GnAppAccessLogAction.sendGnAppAccessLog send msg to amq queue, gnAppAccessLog={}"
					, new Object[]{gnAppAccessLog});
			result.setState(BaseResult.SUCCESS);
			
		} catch (Exception e) {
			result.setState(BaseResult.FAIL);
			logger.error("GnAppAccessLogAction.sendGnAppAccessLog found Exception", e);
		}
		
		printJson2(result, false, false);
		
	}
	
}
