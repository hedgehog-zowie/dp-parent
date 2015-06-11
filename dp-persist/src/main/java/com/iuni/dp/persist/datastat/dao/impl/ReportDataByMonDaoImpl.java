package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datareport.model.ReportDataByMon;
import com.iuni.dp.persist.datastat.dao.ReportDataByMonDao;

/**
 * ReportDataByMon for Statistics DAO 
 * @author Kenneth.Cai@iuni.com
 * @version ips-V1.1.0
 */
@Repository("reportDataByMon4StatDao")
public class ReportDataByMonDaoImpl extends
		BaseDaoImpl<ReportDataByMon, Serializable> implements
		ReportDataByMonDao {

	private final Logger logger = LoggerFactory.getLogger(ReportDataByMonDaoImpl.class); 
	
	@Override
	public List<Map<String, Object>> selectNetflowSumToday(
			Map<String, Object> params) {
		logger.debug("ReportDataByMonDaoImpl.selectNetflowSumToday(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(ReportDataByMon.class.getSimpleName() 
				+ ".selectNetflowSumToday", params);
		
		logger.debug("ReportDataByMonDaoImpl.selectNetflowSumToday(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

}
