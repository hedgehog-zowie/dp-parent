package com.iuni.dp.service.datareport.service2;

import java.util.List;

import com.iuni.dp.service.datareport.constants.ReportDataType;

/**
 * 上报数据至Queue的Service接口
 * @author CaiKe
 * @param <T>
 * @version dp-service-1.0.0
 */
public interface ReportData2QueueService<T> {

	/**
	 * 单条上报数据入队列
	 * @param String pageData 页面上报数据
	 * @return void
	 */
	public void reportDataPutQueue(ReportDataType rptDataType, T reportData);
	
	/**
	 * 多条上报数据批量入队列
	 * @param List<String> pageDataList 页面上报数据列表
	 * @return void
	 */
	public void reportDataBatchPutQueue(ReportDataType rptDataType, List<T> reportDataList);
	
}
