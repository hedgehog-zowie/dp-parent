package com.iuni.dp.service.datastat.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iuni.dp.persist.datastat.dao.GnAppAccessLogDao;
import com.iuni.dp.persist.datastat.model.GnAppAccessLog;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.GnAppAccessLogService;

/**
 * 金立相关APP客户端登入登出记录日志Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("gnAppAccessLog4StatService")
public class GnAppAccessLogServiceImpl implements GnAppAccessLogService {

	private static final Logger logger = LoggerFactory.getLogger(GnAppAccessLogServiceImpl.class);
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
//	private static final String StatByDay = "byDay";
	
	private static final String StatByMonth = "byMonth";
	
	@Autowired
	private GnAppAccessLogDao gnAppAccessLog4StatDao;
	
	@Override
	public List<Map<String, Object>> fetchChannelTrendByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectChannelTrendByExample(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchChannelTrendByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchChannelTrendByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> fetchChannelTrendByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectChannelTrendByPage(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchChannelTrendByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchChannelTrendByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer fetchChannelTrendCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = gnAppAccessLog4StatDao.selectChannelTrendCount(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchChannelTrendCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchChannelTrendCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Map<String, List<Map<String, Object>>> fetchRegisterStatMapByChannel(
			List<Map<String, Object>> list, Map<String, Object> params) {
		String beginDate = (String) params.get("beginDate");
		String endDate = (String) params.get("endDate");
		if(CollectionUtils.isEmpty(list) || StringUtils.isBlank(beginDate) 
				|| StringUtils.isBlank(endDate)) {
			return null;
		}
		
		// 根据查询条件的开始及结束时间构造初始Chart数据结果集
		List<Map<String, Object>> initList = new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> initMap = null;
			Date sDate = dateFormat.parse(beginDate);
			Date eDate = dateFormat.parse(endDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sDate);
			
			initMap = new HashMap<String, Object>();
			initMap.put("registerNum", BigDecimal.ZERO);
			initMap.put("bizDate", dateFormat.format(cal.getTime()));
			initList.add(initMap);
			
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
			while(cal.getTime().compareTo(eDate) <= 0) {
				initMap = new HashMap<String, Object>();
				initMap.put("registerNum", BigDecimal.ZERO);
				initMap.put("bizDate", dateFormat.format(cal.getTime()));
				initList.add(initMap);
				cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
			}
			
		} catch (ParseException e) {
			logger.error("GnAppAccessLogServiceImpl.fetchRegisterStatMapByChannel found ParseException.", e);
			throw new ServiceException(e);
		}
		
		// 根据ChannelName渠道名分割原始DB查询结果集
		Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
		List<Map<String, Object>> mapValues = new ArrayList<Map<String, Object>>();
		
		if(list.size() == 1) {
	        mapValues.add(list.get(0));
	        result.put((String)list.get(0).get("channelName"), mapValues);
	    } else {
	    	for(int i = 0; i < list.size() - 1; i++) {
	    		String preChannel = (String) list.get(i).get("channelName");
	    		String nextChannel = (String) list.get(i+1).get("channelName");
	    		
	    		if(StringUtils.isBlank(preChannel)) {
	    			continue;
	    		}
	    		
	    		mapValues.add(list.get(i));
	    		
	    		if(!preChannel.equals(nextChannel)) {
	    			result.put(preChannel, mapValues);
	    			mapValues = new ArrayList<Map<String, Object>>();
	    		}
	    		
	    		if(i == list.size() - 2) {
	    			mapValues.add(list.get(i+1));
	    			result.put(nextChannel, mapValues);
	    		}
	    	}
	    }
		
		// 合并初始Chart数据结果集和根据渠道名分割后的原始DB查询结果集
		for(Entry<String,List<Map<String,Object>>> entry : result.entrySet()) {
			List<Map<String,Object>> channelList =  entry.getValue();
			
			for(Map<String,Object> initMap : initList) {
				String bizDate = (String) initMap.get("bizDate");
				boolean isExist = false;
				for(Map<String,Object> channelMap : channelList) {
					if(channelMap.containsValue(bizDate)) {
						isExist = true;
						break;
					}
				}
				if(!isExist) {
					channelList.add(initMap);
				}
			}
			
			Collections.sort(channelList, new Comparator<Map<String, Object>>() {
				@Override
				public int compare(Map<String, Object> preMap,
						Map<String, Object> nextMap) {
					int result = 0;
					try {
						Date preBizDate = dateFormat.parse((String) preMap.get("bizDate"));
						Date nextBizDate = dateFormat.parse((String) nextMap.get("bizDate"));
						result = preBizDate.compareTo(nextBizDate);
					} catch (ParseException e) {
						logger.error("GnAppAccessLogServiceImpl.fetchRegisterStatMapByChannel found ParseException.", e);
						throw new ServiceException(e);
					}
					
					return result;
				}
			});
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> fetchRegisterOfChannelByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectRegisterOfChannelByExample(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchRegisterOfChannelByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchRegisterOfChannelByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> fetchRegisterOfChannelByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectRegisterOfChannelByPage(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchRegisterOfChannelByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchRegisterOfChannelByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer fetchRegisterOfChannelCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = gnAppAccessLog4StatDao.selectRegisterOfChannelCount(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchRegisterOfChannelCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchRegisterOfChannelCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	/**
	 * 转换yyyy-MM-dd至yyyy-MM
	 * @param date
	 * @return
	 */
	private String getMonthModeFromDate(String date) {
		String result = null;
		String[] dateDetails = date.split("-");
		if(null != dateDetails && dateDetails.length == 3) {
			result = dateDetails[0] + "-" + dateDetails[1];
		}
		return result;
	}
	
	@Override
	public Map<String, List<Map<String, Object>>> fetchActiveUserStatMapByChannel(
			List<Map<String, Object>> list, Map<String, Object> params, final String statRate) {
		String beginDate = (String) params.get("beginDate");
		String endDate = (String) params.get("endDate");
		if(CollectionUtils.isEmpty(list) || StringUtils.isBlank(beginDate) 
				|| StringUtils.isBlank(endDate)) {
			return null;
		}
		
		// 根据查询条件的开始及结束时间构造初始Chart数据结果集
		List<Map<String, Object>> initList = new ArrayList<Map<String, Object>>();
		
		if(StatByMonth.equals(statRate)) {
			try {
				Map<String, Object> initMap = null;
				Date sDate = dateFormat.parse(beginDate + "-01");
				Date eDate = dateFormat.parse(endDate + "-01");
				Calendar cal = Calendar.getInstance();
				cal.setTime(sDate);
				
				initMap = new HashMap<String, Object>();
				initMap.put("activeUserNum", BigDecimal.ZERO);
				initMap.put("bizDate", getMonthModeFromDate(dateFormat.format(cal.getTime())));
				initList.add(initMap);
				
				cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
				while(cal.getTime().compareTo(eDate) <= 0) {
					initMap = new HashMap<String, Object>();
					initMap.put("activeUserNum", BigDecimal.ZERO);
					initMap.put("bizDate", getMonthModeFromDate(dateFormat.format(cal.getTime())));
					initList.add(initMap);
					cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
				}
				
			} catch (ParseException e) {
				logger.error("GnAppAccessLogServiceImpl.fetchRegisterStatMapByChannel found ParseException.", e);
				throw new ServiceException(e);
			}
			
		} else {
			try {
				Map<String, Object> initMap = null;
				Date sDate = dateFormat.parse(beginDate);
				Date eDate = dateFormat.parse(endDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(sDate);
				
				initMap = new HashMap<String, Object>();
				initMap.put("activeUserNum", BigDecimal.ZERO);
				initMap.put("bizDate", dateFormat.format(cal.getTime()));
				initList.add(initMap);
				
				cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
				while(cal.getTime().compareTo(eDate) <= 0) {
					initMap = new HashMap<String, Object>();
					initMap.put("activeUserNum", BigDecimal.ZERO);
					initMap.put("bizDate", dateFormat.format(cal.getTime()));
					initList.add(initMap);
					cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
				}
				
			} catch (ParseException e) {
				logger.error("GnAppAccessLogServiceImpl.fetchRegisterStatMapByChannel found ParseException.", e);
				throw new ServiceException(e);
			}
		}
		
		// 根据ChannelName渠道名分割原始DB查询结果集
		Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
		List<Map<String, Object>> mapValues = new ArrayList<Map<String, Object>>();
		
		if(list.size() == 1) {
	        mapValues.add(list.get(0));
	        result.put((String)list.get(0).get("channelName"), mapValues);
	    } else {
	    	for(int i = 0; i < list.size() - 1; i++) {
	    		String preChannel = (String) list.get(i).get("channelName");
	    		String nextChannel = (String) list.get(i+1).get("channelName");
	    		
	    		if(StringUtils.isBlank(preChannel)) {
	    			continue;
	    		}
	    		
	    		mapValues.add(list.get(i));
	    		
	    		if(!preChannel.equals(nextChannel)) {
	    			result.put(preChannel, mapValues);
	    			mapValues = new ArrayList<Map<String, Object>>();
	    		}
	    		
	    		if(i == list.size() - 2) {
	    			mapValues.add(list.get(i+1));
	    			result.put(nextChannel, mapValues);
	    		}
	    	}
	    }
		
		// 合并初始Chart数据结果集和根据渠道名分割后的原始DB查询结果集
		for(Entry<String,List<Map<String,Object>>> entry : result.entrySet()) {
			List<Map<String,Object>> channelList =  entry.getValue();
			
			for(Map<String,Object> initMap : initList) {
				String bizDate = (String) initMap.get("bizDate");
				boolean isExist = false;
				for(Map<String,Object> channelMap : channelList) {
					if(channelMap.containsValue(bizDate)) {
						isExist = true;
						break;
					}
				}
				if(!isExist) {
					channelList.add(initMap);
				}
			}
			
			Collections.sort(channelList, new Comparator<Map<String, Object>>() {
				@Override
				public int compare(Map<String, Object> preMap,
						Map<String, Object> nextMap) {
					int result = 0;
					try {
						if(StatByMonth.equals(statRate)) {
							Date preBizDate = dateFormat.parse((String) preMap.get("bizDate") + "-01");
							Date nextBizDate = dateFormat.parse((String) nextMap.get("bizDate") + "-01");
							result = preBizDate.compareTo(nextBizDate);
						} else {
							Date preBizDate = dateFormat.parse((String) preMap.get("bizDate"));
							Date nextBizDate = dateFormat.parse((String) nextMap.get("bizDate"));
							result = preBizDate.compareTo(nextBizDate);
						}
					} catch (ParseException e) {
						logger.error("GnAppAccessLogServiceImpl.fetchRegisterStatMapByChannel found ParseException.", e);
						throw new ServiceException(e);
					}
					
					return result;
				}
			});
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> fetchActiveUserOfChannelDailyByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectActiveUserOfChannelDailyByExample(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchActiveUserOfChannelDailyByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchActiveUserOfChannelDailyByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> fetchActiveUserOfChannelDailyByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectActiveUserOfChannelDailyByPage(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchActiveUserOfChannelDailyByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchActiveUserOfChannelDailyByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer fetchActiveUserOfChannelDailyCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = gnAppAccessLog4StatDao.selectActiveUserOfChannelDailyCount(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchActiveUserOfChannelDailyCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchActiveUserOfChannelDailyCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> fetchActiveUserOfChannelMonthlyByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectActiveUserOfChannelMonthlyByExample(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchActiveUserOfChannelMonthlyByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchActiveUserOfChannelMonthlyByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> fetchActiveUserOfChannelMonthlyByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectActiveUserOfChannelMonthlyByPage(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchActiveUserOfChannelMonthlyByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchActiveUserOfChannelMonthlyByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer fetchActiveUserOfChannelMonthlyCount(
			Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = gnAppAccessLog4StatDao.selectActiveUserOfChannelMonthlyCount(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchActiveUserOfChannelMonthlyCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchActiveUserOfChannelMonthlyCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> fetchLaunchNumDistribution(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectLaunchNumDistribution(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchLaunchNumDistribution invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchLaunchNumDistribution found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryAppUserStatOnModelByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectAppUserStatOnModelByExample(params);
			logger.debug("GnAppAccessLogServiceImpl.queryAppUserStatOnModelByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryAppUserStatOnModelByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryAppUserStatOnModelByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectAppUserStatOnModelByPage(params);
			logger.debug("GnAppAccessLogServiceImpl.queryAppUserStatOnModelByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryAppUserStatOnModelByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryAppUserStatOnModelCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = gnAppAccessLog4StatDao.selectAppUserStatOnModelCount(params);
			logger.debug("GnAppAccessLogServiceImpl.queryAppUserStatOnModelCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryAppUserStatOnModelCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> fetchChannelList(Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectChannelList(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchChannelList invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchChannelList found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> fetchApkVersionList(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectApkVersionList(params);
			logger.debug("GnAppAccessLogServiceImpl.fetchApkVersionList invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.fetchApkVersionList found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniOSTotalUserByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectIuniOSTotalUserByExample(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSTotalUserByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSTotalUserByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniOSTotalUserByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectIuniOSTotalUserByPage(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSTotalUserByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSTotalUserByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIuniOSTotalUserCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = gnAppAccessLog4StatDao.selectIuniOSTotalUserCount(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSTotalUserCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSTotalUserCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIuniOSAppNames(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectIuniOSAppNames(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSAppNames invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSAppNames found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniOSApkVersions(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectIuniOSApkVersions(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSApkVersions invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSApkVersions found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniOSUserStatByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectIuniOSUserStatByExample(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSUserStatByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSUserStatByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniOSUserStatByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectIuniOSUserStatByPage(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSUserStatByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSUserStatByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIuniOSUserStatCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = gnAppAccessLog4StatDao.selectIuniOSUserStatCount(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSUserStatCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSUserStatCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIuniOSUserStatOnPeriodByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectIuniOSUserStatOnPeriodByExample(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSUserStatOnPeriodByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSUserStatOnPeriodByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniOSUserStatOnPeriodByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectIuniOSUserStatOnPeriodByPage(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSUserStatOnPeriodByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSUserStatOnPeriodByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIuniOSUserStatOnPeriodCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = gnAppAccessLog4StatDao.selectIuniOSUserStatOnPeriodCount(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSUserStatOnPeriodCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSUserStatOnPeriodCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIuniOSVersionDistributeByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectIuniOSVersionDistributeByExample(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSVersionDistributeByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSVersionDistributeByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniOSVersionDistributeByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectIuniOSVersionDistributeByPage(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSVersionDistributeByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSVersionDistributeByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIuniOSVersionDistributeCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = gnAppAccessLog4StatDao.selectIuniOSVersionDistributeCount(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSVersionDistributeCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSVersionDistributeCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIuniOSAreaDistributeByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectIuniOSAreaDistributeByExample(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSAreaDistributeByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSAreaDistributeByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniOSAreaDistributeByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppAccessLog4StatDao.selectIuniOSAreaDistributeByPage(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSAreaDistributeByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSAreaDistributeByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIuniOSAreaDistributeCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = gnAppAccessLog4StatDao.selectIuniOSAreaDistributeCount(params);
			logger.debug("GnAppAccessLogServiceImpl.queryIuniOSAreaDistributeCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.queryIuniOSAreaDistributeCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Integer saveGnAppAccessLog(GnAppAccessLog gnAppAccessLog) {
		Integer execCount = 0;
		
		try {
			execCount = gnAppAccessLog4StatDao.insertGnAppAccessLog4Stat(gnAppAccessLog);
			logger.debug("GnAppAccessLogServiceImpl.saveGnAppAccessLog invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.saveGnAppAccessLog found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeGnAppAccessLogById(Long id) {
		Integer execCount = 0;
		
		try {
			execCount = gnAppAccessLog4StatDao.deleteGnAppAccessLog4StatById(id);
			logger.debug("GnAppAccessLogServiceImpl.removeGnAppAccessLogById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.removeGnAppAccessLogById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

}
