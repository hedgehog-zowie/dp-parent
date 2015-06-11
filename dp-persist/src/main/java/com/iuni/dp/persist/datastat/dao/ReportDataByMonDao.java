package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datareport.model.ReportDataByMon;

/**
 * ReportDataByMon DAO for Statistics
 * @author Kenneth.Cai@iuni.com
 * @version ips-V1.1.0
 */
public interface ReportDataByMonDao extends BaseDao<ReportDataByMon, Serializable> {

	/**
	 * 获取当天上报数据流量数据汇总
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectNetflowSumToday(Map<String, Object> params);
	
}
