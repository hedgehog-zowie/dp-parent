package com.iuni.dp.service.datastat.service.wmswl;

import java.util.List;
import java.util.Map;

/**
 *  WMS(物流)调拨明细统计报表 业务接口
* @author dan.wang@iuni.com
 *
 */
public interface IuniWmsTransferStatForWlService {
	
	public Integer queryIuniWmsTransferStatForWlCount(Map<String, Object> params);

	public List<Map<String, Object>> queryIuniWmsTransferStatForWlByPage(
			Map<String, Object> queryParams);

	public List<Map<String, Object>> queryIuniWmsTransferForWL2Excel(
			Map<String, Object> queryParams);

	public List<Map<String, Object>> queryIuniWmsReceiveForWLByPage(Map<String, Object> queryParams);

	public Integer queryIuniWmsReceiveForWLCount(
			Map<String, Object> queryParams2);

	public List<Map<String, Object>> queryIuniWmsReceiveForWL2Excel(
			Map<String, Object> queryParams2);
}
