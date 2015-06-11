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

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iuni.dp.persist.datastat.dao.GnAppImeiLogDao;
import com.iuni.dp.persist.datastat.model.GnAppImeiLog;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.GnAppImeiLogService;

/**
 * 金立相关APP客户端IMEI首次启用时间日志Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("gnAppImeiLog4StatService")
public class GnAppImeiLogServiceImpl implements GnAppImeiLogService {

	private static final Logger logger = LoggerFactory.getLogger(GnAppImeiLogServiceImpl.class);
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Calendar calendar = Calendar.getInstance();
	
	@Autowired
	private GnAppImeiLogDao gnAppImeiLog4StatDao; 
	
	@Override
	public Map<String, List<Map<String, Object>>> fetchNewUserStatMapByChannel(
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
			initMap.put("newUserNum", BigDecimal.ZERO);
			initMap.put("bizDate", dateFormat.format(cal.getTime()));
			initList.add(initMap);
			
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
			while(cal.getTime().compareTo(eDate) <= 0) {
				initMap = new HashMap<String, Object>();
				initMap.put("newUserNum", BigDecimal.ZERO);
				initMap.put("bizDate", dateFormat.format(cal.getTime()));
				initList.add(initMap);
				cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
			}
			
		} catch (ParseException e) {
			logger.error("GnAppImeiLogServiceImpl.fetchNewUserStatMapByChannel found ParseException.", e);
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
						logger.error("GnAppImeiLogServiceImpl.fetchNewUserStatMapByChannel found ParseException.", e);
						throw new ServiceException(e);
					}
					
					return result;
				}
			});
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> fetchNewUserOfChannelByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppImeiLog4StatDao.selectNewUserOfChannelByExample(params);
			logger.debug("GnAppImeiLogServiceImpl.fetchNewUserOfChannelByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppImeiLogServiceImpl.fetchNewUserOfChannelByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> fetchNewUserOfChannelByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppImeiLog4StatDao.selectNewUserOfChannelByPage(params);
			logger.debug("GnAppImeiLogServiceImpl.fetchNewUserOfChannelByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppImeiLogServiceImpl.fetchNewUserOfChannelByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer fetchNewUserOfChannelCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = gnAppImeiLog4StatDao.selectNewUserOfChannelCount(params);
			logger.debug("GnAppImeiLogServiceImpl.fetchNewUserOfChannelCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppImeiLogServiceImpl.fetchNewUserOfChannelCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIuniOSRemainUserOnDayByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			List<Map<String, Object>> data = gnAppImeiLog4StatDao.selectIuniOSRemainUserOnDayByExample(params);
			list = mergeIuniOSRemainUserOnDayData(data, params);
			logger.debug("GnAppImeiLogServiceImpl.queryIuniOSRemainUserOnDayByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppImeiLogServiceImpl.queryIuniOSRemainUserOnDayByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> queryIuniOSRemainUserOnDayByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppImeiLog4StatDao.selectIuniOSRemainUserOnDayByPage(params);
//			List<Map<String, Object>> data = gnAppImeiLog4StatDao.selectIuniOSRemainUserOnDayByPage(params);
//			list = mergeIuniOSRemainUserOnDayData(data, params);
			logger.debug("GnAppImeiLogServiceImpl.queryIuniOSRemainUserOnDayByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppImeiLogServiceImpl.queryIuniOSRemainUserOnDayByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIuniOSRemainUserOnDayCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = gnAppImeiLog4StatDao.selectIuniOSRemainUserOnDayCount(params);
//			if(null != params.get("beginDate") || null != params.get("endDate")) {
//				totalCount = calcDays4Peroid((String)params.get("beginDate"), (String)params.get("endDate"));
//			}
			
			logger.debug("GnAppImeiLogServiceImpl.queryIuniOSRemainUserOnDayCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppImeiLogServiceImpl.queryIuniOSRemainUserOnDayCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}
	
	@Override
	public Map<String, List<Map<String, Object>>> queryIuniOSRemainUserOnDay4Chart(
			List<Map<String, Object>> list, Map<String, Object> params) {
		Map<String, List<Map<String, Object>>> chartData = null;
		
		if(CollectionUtils.isEmpty(list) || MapUtils.isEmpty(params)) {
			return chartData;
		}
		
		chartData = new HashMap<String, List<Map<String, Object>>>();
		Integer remainDays = (Integer) params.get("remainDays");
		String chartItem = "";
		if(remainDays == 1) {
			chartItem = "每日留存率";
		} else if(remainDays == 7) {
			chartItem = "7日留存率";
		} else if(remainDays == 30) {
			chartItem = "30日留存率";
		} else {
			chartItem = "留存率";
		}
		
		List<Map<String, Object>> remainUserRateList = new ArrayList<Map<String, Object>>();
		chartData.put(chartItem, remainUserRateList);
		
		for(Map<String, Object> map : list) {
			String statDate = (String) map.get("statDate");
//			Integer newUserNum = (Integer) map.get("newUserNum");
//			Integer remainUserNum = (Integer) map.get("remainUserNum");
			
			Map<String, Object> remainUserRateMap = new HashMap<String, Object>();
			remainUserRateList.add(remainUserRateMap);
			remainUserRateMap.put("statDate", statDate);
			if(map.get("remainUserRate") instanceof Integer) {
				Integer remainUserRate = ((Integer) map.get("remainUserRate"))*100;
				remainUserRateMap.put("remainUserRate", remainUserRate);
			} else if(map.get("remainUserRate") instanceof BigDecimal) {
				BigDecimal remainUserRate = ((BigDecimal) map.get("remainUserRate")).multiply(new BigDecimal(100));
				remainUserRateMap.put("remainUserRate", remainUserRate);
			}
		}
		
		return chartData;
	}
	
	/**
	 * 合并补全数据库查询数据
	 */
	protected List<Map<String, Object>> mergeIuniOSRemainUserOnDayData(
			List<Map<String, Object>> data, Map<String, Object> params) {
		List<Map<String, Object>> initData = new ArrayList<Map<String, Object>>();
		String beginDate = (String) params.get("beginDate");
		String endDate = (String) params.get("endDate");
		
		Map<String, Object> map = null;
		int days = 0;
		try {
			Date sDate = dateFormat.parse(beginDate);
			Date eDate = dateFormat.parse(endDate);
			
			days = Integer.parseInt(String.valueOf((eDate.getTime()-sDate.getTime())/(24*3600*1000)));
			
			calendar.setTime(sDate);
			int temp = 0;
			for(int i = 0; i <= days; i++) {
				temp = i;
				map = new HashMap<String, Object>();
				initData.add(map);
				if(i == 0) {
					calendar.add(Calendar.DAY_OF_MONTH, 0);
				} else {
					calendar.add(Calendar.DAY_OF_MONTH, 1);
				}
				String statDate = dateFormat.format(calendar.getTime());
				map.put("statDate", statDate);
				map.put("newUserNum", BigDecimal.ZERO);
				map.put("remainUserNum", BigDecimal.ZERO);
				map.put("remainUserRate", BigDecimal.ZERO);
				map.put("rowNum", temp+1);
			}
		} catch (ParseException e) {
			logger.error("GnAppImeiLogServiceImpl.calcDays4Peroid found ParseException.", e);
//			throw new BusinessException(e);
		}
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> initMap : initData) {
			String statDate_1 = (String) initMap.get("statDate");
			for(Map<String, Object> dataMap : data) {
				String statDate_2 = (String) dataMap.get("statDate");
				if(statDate_2.equals(statDate_1)) {
					dataMap.put("rowNum", (Integer)initMap.get("rowNum"));
					initMap = dataMap;
					break;
				}
			}
			result.add(initMap);
		}
		
		return result;
	}
	
	/**
	 * 计算时间段天数
	 * @param beginDate
	 * @param endDate
	 * @return int
	 */
	protected int calcDays4Peroid(String beginDate, String endDate) {
		int days = 0;
		try {
			Date sDate = dateFormat.parse(beginDate);
			Date eDate = dateFormat.parse(endDate);
			
			days = Integer.parseInt(String.valueOf((eDate.getTime()-sDate.getTime())/(24*3600*1000)));
		} catch (ParseException e) {
			logger.error("GnAppImeiLogServiceImpl.calcDays4Peroid found ParseException.", e);
//			throw new BusinessException(e);
		}
		return days;
	}

	@Override
	public Integer saveGnAppImeiLog(GnAppImeiLog gnAppImeiLog) {
		Integer execCount = 0;
		
		try {
			execCount = gnAppImeiLog4StatDao.insertGnAppImeiLog4Stat(gnAppImeiLog);
			logger.debug("GnAppImeiLogServiceImpl.saveGnAppImeiLog invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppImeiLogServiceImpl.saveGnAppImeiLog found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeGnAppImeiLogById(Long id) {
		Integer execCount = 0;
		
		try {
			execCount = gnAppImeiLog4StatDao.deleteGnAppImeiLog4StatById(id);
			logger.debug("GnAppImeiLogServiceImpl.removeGnAppImeiLogById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppImeiLogServiceImpl.removeGnAppImeiLogById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

}
