package com.iuni.dp.service.datareport.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.iuni.dp.persist.datareport.model.GnAppAccessLog;
import com.iuni.dp.persist.datareport.model.ReportDataByMon;
import com.iuni.dp.persist.datastat.model.IuniUcLoginLog;
import com.iuni.dp.persist.datastat.model.IuniUcOloginLog;
import com.iuni.dp.service.common.utils.DateUtil2;
import com.iuni.dp.service.datareport.constants.ReportDataState;

/**
 * 页面上报数据文件解析器
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public class FileStringParser {

	private static final SimpleDateFormat timeformat_1 = new SimpleDateFormat(DateUtil2.TimestampFormat_2);
	
	/**
	 * 将ReportData类型上报数据文件读取的List< String >转换为List< ReportDataByMon >
	 * @param List< String > dataList
	 * @return Map< ReportDataState, List<ReportDataByMon > >
	 */
	public static Map<ReportDataState, List<ReportDataByMon>> fromStringToCmRptData(List<String> dataList) {
		Map<ReportDataState, List<ReportDataByMon>> result = null;
		
		if(CollectionUtils.isEmpty(dataList)) {
			return result;
		}
		
		result = new HashMap<ReportDataState, List<ReportDataByMon>>();
		List<ReportDataByMon> normalList = new ArrayList<ReportDataByMon>();
		List<ReportDataByMon> abnormalList = new ArrayList<ReportDataByMon>();
		result.put(ReportDataState.Normal, normalList);
		result.put(ReportDataState.Abnormal, abnormalList);
		
		for(String data : dataList) {
			ReportDataByMon rptData = JSON.toJavaObject(JSON.parseObject(data), ReportDataByMon.class);
			String sourceId = rptData.getSourceId();
			String rptType = rptData.getReportType();
			String rptContent = rptData.getReportContent();
			if (StringUtils.isBlank(sourceId) || StringUtils.isBlank(rptType)
					|| StringUtils.isBlank(rptContent)) {
				abnormalList.add(rptData);
				continue;
			}
			normalList.add(rptData);
		}
		
		return result;
	}
	
	/**
	 * 将ReportData类型上报数据文件读取的List< String >转换为List< GnAppAccessLog >
	 * @param dataList
	 * @return Map< ReportDataState, List< GnAppAccessLog > >
	 */
	public static Map<ReportDataState, List<GnAppAccessLog>> fromStringToGnAppRptData(List<String> dataList) {
		Map<ReportDataState, List<GnAppAccessLog>> result = null;
		
		if(CollectionUtils.isEmpty(dataList)) {
			return result;
		}
		
		result = new HashMap<ReportDataState, List<GnAppAccessLog>>();
		List<GnAppAccessLog> normalList = new ArrayList<GnAppAccessLog>();
		List<GnAppAccessLog> abnormalList = new ArrayList<GnAppAccessLog>();
		result.put(ReportDataState.Normal, normalList);
		result.put(ReportDataState.Abnormal, abnormalList);
		
		for(String data : dataList) {
			GnAppAccessLog rptData = JSON.toJavaObject(JSON.parseObject(data), GnAppAccessLog.class);
			String appName = rptData.getAppName();
			String channelName = rptData.getChannelName();
			String apkVersion = rptData.getApkVersion();
//			String imei = rptData.getImei();
			if (StringUtils.isBlank(appName) || StringUtils.isBlank(channelName)
					|| StringUtils.isBlank(apkVersion)) {
				abnormalList.add(rptData);
				continue;
			}
			normalList.add(rptData);
		}
		
		return result;
	}
	
	/**
	 * Convert String to IuniUcLoginLog Bean
	 * @param dataList
	 * @return List
	 * @throws ParseException
	 */
	public static List<IuniUcLoginLog> fromStringToLoginLog(List<String> dataList) {
		List<IuniUcLoginLog> result = new ArrayList<IuniUcLoginLog>();
		if(CollectionUtils.isEmpty(dataList)) {
			return result;
		}
		
		for(String data : dataList) {
			try {
				String[] dataDetail = data.split(",");
				if(null != dataDetail && dataDetail.length >= 6) {
					String userName = dataDetail[0];
					Integer loginError = Integer.valueOf(dataDetail[1]);
					String loginResult = dataDetail[2];
					String loginFrom = dataDetail[3];
					String loginIp = dataDetail[4];
					Timestamp loginTime = null;
					String userId = null;
					if(null != dataDetail[5]) {
						loginTime = new Timestamp(timeformat_1.parse(dataDetail[5]).getTime());
					}
					if(dataDetail.length >= 7) {
						userId = dataDetail[6];
					}
					IuniUcLoginLog iuniUcLoginLog = new IuniUcLoginLog(userId,
							userName, null, null, loginResult, loginError,
							loginFrom, loginIp, loginTime);
					result.add(iuniUcLoginLog);
				}
				
			} catch (NumberFormatException e) {
				continue;
			} catch (ParseException e) {
				continue;
			} 
		}
		
		return result;
	}
	
	/**
	 * Convert String to IuniUcOloginLog Bean
	 * @param dataList
	 * @return List
	 */
	public static List<IuniUcOloginLog> fromStringToOloginLog(List<String> dataList) {
		List<IuniUcOloginLog> result = new ArrayList<IuniUcOloginLog>();
		if(CollectionUtils.isEmpty(dataList)) {
			return result;
		}
		
		for(String data : dataList) {
			try {
				String[] dataDetail = data.split(",");
				if(null != dataDetail && dataDetail.length >= 6) {
					String oType = dataDetail[0];
					String loginResult = dataDetail[1];
					Boolean isBind = new Boolean(dataDetail[2]);
					String loginIp = dataDetail[3];
					String userId = dataDetail[4];
					Timestamp loginTime = null;
					if(null != dataDetail[5]) {
						loginTime = new Timestamp(timeformat_1.parse(dataDetail[5]).getTime());
					}
					
					IuniUcOloginLog iuniUcOloginLog = new IuniUcOloginLog(userId, loginResult, oType,
							isBind, loginIp, loginTime);
					result.add(iuniUcOloginLog);
				}
				
			} catch (ParseException e) {
				continue;
			} 
		}
		
		return result;
	}
	
}
