package com.iuni.dp.service.datareport.service;

import java.util.List;

/**
 * 上报数据Service接口
 * @author CaiKe
 * @param <T>
 * @version dp-service-1.0.0
 */
public interface ReportDataByMonService<T> {

	/**
	 * 保存上报数据
	 * @param ReportData reportData
	 */
	public Integer saveReportData(T reportData);
	
	/**
	 * 批量保存上报数据
	 * @param List<StpPageDataReport> dataList
	 */
	public Integer batchSaveReportData(List<T> reportDataList);
	
	/**
	 * 保存异常上报数据
	 * @param ReportData reportData
	 */
	public Integer saveReportDataEx(T reportData);
	
	/**
	 * 批量异常保存上报数据
	 * @param List<StpPageDataReport> dataList
	 */
	public Integer batchSaveReportDataEx(List<T> reportDataList);
	
}
