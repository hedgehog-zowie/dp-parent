package com.iuni.dp.service.datastat.service.wmswl;

import java.util.List;
import java.util.Map;

/**
 * WMS（物流）销售出库明细报表 业务接口
 * @author dan.wang@iuni.com
 *
 */
public interface IuniWmsStockMoveViewForWlService {
	/**
	 * 销售出库明细报表  分页查询
	 * @param queryParams
	 * @return
	 */
	List<Map<String, Object>> queryIuniWmsStockMoveForWl(
			Map<String, Object> queryParams);
	
	/**
	 * 销售出库明细报表  总数量
	 * @param queryParams
	 * @return
	 */
	Integer queryIuniWmsStockMoveForWlCount(Map<String, Object> queryParams);
	
	/**
	 * 销售出库明细报表  导出到Excel
	 * @param queryParams
	 * @return
	 */
	List<Map<String, Object>> queryIuniWmsStockMoveForWl2Excel(
			Map<String, Object> queryParams);

}
