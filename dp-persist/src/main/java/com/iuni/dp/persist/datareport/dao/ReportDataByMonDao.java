package com.iuni.dp.persist.datareport.dao;

import java.util.List;

import com.iuni.dp.persist.datareport.model.ReportDataByMon;

/**
 * ReportDataByMon类型DAO接口
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public interface ReportDataByMonDao {

	/**
	 * 按月保存上报数据
	 * @param ReportData reportData
	 */
	public Integer insertReportDataByMon(ReportDataByMon reportDataByMon);
	
	/**
	 * 按月批量保存上报数据
	 * @param List<StpPageDataReport> dataList
	 */
	public Integer batchInsertReportDataByMon(final List<ReportDataByMon> reportDataByMonList);
	
	/**
	 * 保存异常上报数据
	 * @param ReportData reportData
	 */
	public Integer insertReportDataEx(ReportDataByMon reportDataByMon);
	
	/**
	 * 批量异常保存上报数据
	 * @param List<StpPageDataReport> dataList
	 */
	public Integer batchInsertReportDataEx(final List<ReportDataByMon> reportDataByMonList);
	
}
