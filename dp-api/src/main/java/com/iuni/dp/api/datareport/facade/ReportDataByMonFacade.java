package com.iuni.dp.api.datareport.facade;

import java.util.List;

import com.iuni.dp.service.common.bean.BaseResult;
import com.iuni.dp.service.datareport.constants.ReportDataType;

/**
 * 上报数据Facade接口, 供第三方统一调用
 * @author CaiKe
 * @version V1.0.0
 */
public interface ReportDataByMonFacade<T> {

	/**
	 * 单条数据上报
	 * @param String pageData
	 * @return Result
	 */
	public BaseResult reportData(ReportDataType rptDataType, T reportData);
	
	/**
	 * 批量数据上报
	 * @param List<String> pageDataList
	 * @return Result
	 */
	public BaseResult batchReportData(ReportDataType rptDataType, List<T> reportDataList);
	
}
