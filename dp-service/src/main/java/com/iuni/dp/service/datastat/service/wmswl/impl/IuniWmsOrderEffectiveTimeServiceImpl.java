package com.iuni.dp.service.datastat.service.wmswl.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsOrderEffectiveTimeDao;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsOrderEffectiveTimeService;

@Service("iuniWmsOrderEffectiveTimeService")
public class IuniWmsOrderEffectiveTimeServiceImpl implements IuniWmsOrderEffectiveTimeService {
	
	@Autowired
	private IuniWmsOrderEffectiveTimeDao iuniWmsOrderEffectiveTimeDao;
	private final Logger logger = LoggerFactory.getLogger(IuniWmsOrderEffectiveTimeServiceImpl.class);
	
	@Override
	public List<Map<String, Object>> queryIuniWmsPositiveOrderWl(
			Map<String, Object> queryParams) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list= iuniWmsOrderEffectiveTimeDao.selectIuniWmsOrderEffectiveWlByPage(queryParams);
			//timeCalculate(list);
		} catch (Exception e) {
			logger.error("IuniWmsOrderEffectiveTimeServiceImpl.queryIuniWmsPositiveOrderWl(Map<String, Object> queryParams) exception.",e);
		}
		return list;
	}
	
	@Override
	public Integer queryIuniWmsPositiveOrderWlCount(
			Map<String, Object> queryParams) {
		return iuniWmsOrderEffectiveTimeDao.selectIuniWmsOrderEffectiveWlCount(queryParams);
	}

	@Override
	public List<Map<String, Object>> queryIuniWmsPositiveOrderWl2Excel(
			Map<String, Object> queryParams) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = iuniWmsOrderEffectiveTimeDao.selectIuniWmsOrderEffectiveWl2Excel(queryParams);
			//timeCalculate(list);
		} catch (Exception e) {
			logger.error("IuniWmsOrderEffectiveTimeServiceImpl.queryIuniWmsPositiveOrderWl2Excel(Map<String, Object> queryParams) exception.",e);
		}
		return list;
	}

	private List<Map<String, Object>> timeCalculate(List<Map<String, Object>> list) {
		for(Map<String, Object> m:list){
			Date time1 = (Date)m.get("time1");
			Date time2 = (Date)m.get("time2");
			Date time3 = (Date)m.get("time3");
			Date time4 = (Date)m.get("time4");
			Date time5 = (Date)m.get("time5");
			Date time6 = (Date)m.get("time6");
			//System.out.println(time1+"=="+time2+"=="+time3+"=="+time4+"=="+time5+"=="+time6);
			String time21 = calculateDate(time2,time1);  
			String time32 = calculateDate(time3,time2);  
			String time43 = calculateDate(time4,time3);  
			String time54 = calculateDate(time5,time4);  
			String time65 = calculateDate(time5,time1);  
			String time61 = calculateDate(time6, time1);
			
			m.put("time1", time21);
			m.put("time2", time32);
			m.put("time3", time43);
			m.put("time4", time54);
			m.put("time5", time65);
			m.put("time6", time61);
		}
		return list;
	}


	private String calculateDate(Date time2, Date time1) {
		String ret = null;
		if(time2==null || time1==null){
			return ret;
		}
		long l=time2.getTime()-time1.getTime();
		long day=l/(24*60*60*1000);
		long hour=(l/(60*60*1000)-day*24);
		long min=((l/(60*1000))-day*24*60-hour*60);
		long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		ret = ""+day+"天"+hour+"小时"+min+"分"+s+"秒";
		return ret;
	}
}
