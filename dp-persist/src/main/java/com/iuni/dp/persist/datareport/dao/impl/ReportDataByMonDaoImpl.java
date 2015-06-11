package com.iuni.dp.persist.datareport.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datareport.dao.ReportDataByMonDao;
import com.iuni.dp.persist.datareport.model.ReportDataByMon;

/**
 * ReportDataByMon类型上报数据DAO实现
 * @author CaiKe
 * @version V1.0.0
 */
@Repository("reportDataByMonDao")
public class ReportDataByMonDaoImpl extends BaseDaoImpl<ReportDataByMon, Serializable> implements ReportDataByMonDao {

	private static final Logger logger = LoggerFactory.getLogger(ReportDataByMonDaoImpl.class);
	
	@Override
	public Integer insertReportDataByMon(ReportDataByMon reportDataByMon) {
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		logger.debug("ReportDataByMonDaoImpl.insertReportDataByMon(ReportDataByMon) entry: reportDataByMon={}"
				,new Object[] { reportDataByMon });
		
		insert(reportDataByMon, ReportDataByMon.class.getSimpleName()+".insertReportDataByMon");
		++execCount;
		
		logger.debug("ReportDataByMonDaoImpl.insertReportDataByMon(ReportDataByMon) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

	@SuppressWarnings({"deprecation"})
	@Override
	public Integer batchInsertReportDataByMon(final List<ReportDataByMon> reportDataByMonList) {
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		int dataSize = (reportDataByMonList != null) ? reportDataByMonList.size() : 0;
		logger.debug("ReportDataByMonDaoImpl.batchInsertReportDataByMon(List<ReportDataByMon>) entry: dataSize={}"
				,new Object[] { dataSize });
		
		if (CollectionUtils.isEmpty(reportDataByMonList)) {
			return execCount;
		}
		
		execCount = getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			Integer execCount = 0;
			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for(ReportDataByMon reportDataByMon : reportDataByMonList){
					if(null != reportDataByMon){
						executor.insert(ReportDataByMon.class.getSimpleName()+".insertReportDataByMon", reportDataByMon);
						++execCount;
					}
	            }
	            executor.executeBatch(); 
				
				return execCount;
			}
		});
		
		logger.debug("ReportDataByMonDaoImpl.batchInsertReportDataByMon(List<ReportDataByMon>) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

	@Override
	public Integer insertReportDataEx(ReportDataByMon reportDataByMon) {
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		logger.debug("ReportDataByMonDaoImpl.insertReportDataEx(ReportDataByMon) entry: reportDataByMon={}"
				,new Object[] { reportDataByMon });
		
		insert(reportDataByMon, ReportDataByMon.class.getSimpleName()+".insertReportDataEx");
		++execCount;
		
		logger.debug("ReportDataByMonDaoImpl.insertReportDataEx(ReportDataByMon) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

	@SuppressWarnings({"deprecation"})
	@Override
	public Integer batchInsertReportDataEx(final List<ReportDataByMon> reportDataByMonList) {
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		int dataSize = (reportDataByMonList != null) ? reportDataByMonList.size() : 0;
		logger.debug("ReportDataByMonDaoImpl.batchInsertReportDataEx(List<ReportDataByMon>) entry: dataSize={}"
				,new Object[] { dataSize });
		
		if (CollectionUtils.isEmpty(reportDataByMonList)) {
			return execCount;
		}
		
		execCount = getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			Integer execCount = 0;
			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for(ReportDataByMon reportDataByMon : reportDataByMonList){
					if(null != reportDataByMon){
						executor.insert(ReportDataByMon.class.getSimpleName()+".insertReportDataEx", reportDataByMon);
						++execCount;
					}
	            }
	            executor.executeBatch(); 
				
				return execCount;
			}
		});
		
		logger.debug("ReportDataByMonDaoImpl.batchInsertReportDataEx(List<ReportDataByMon>) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

}
