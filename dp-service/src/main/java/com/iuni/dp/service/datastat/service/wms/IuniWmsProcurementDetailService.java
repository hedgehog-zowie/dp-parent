package com.iuni.dp.service.datastat.service.wms;

import java.util.List;
import java.util.Map;

/**
 * 采购入库明细报表 业务接口
 * @author dan.wang@iuni.com
 *
 */
public interface IuniWmsProcurementDetailService {
	
	/**
	 * 采购入库明细报表 分页查询
	 * @param params
	 * @return
	 */
	public Integer queryIuniWmsProcurementDetailCount(Map<String, Object> params);
	
	/**
	 * 采购入库明细报表 总数量
	 * @param params
	 * @return
	 */
	
	public List<Map<String, Object>> queryIuniWmsProcurementDetailByPage(
			Map<String, Object> params);
	/**
	 * 采购入库明细报表 导出到Excel
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniWmsProcurementDetail2Excel(Map<String, Object> params);
	


}
