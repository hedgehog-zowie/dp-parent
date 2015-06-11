package com.iuni.dp.persist.datastat.dao.wmswl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniWmsReceive;

public interface IuniWmsReceiveDao extends BaseDao<IuniWmsReceive, Serializable> {

	List<Map<String, Object>> selectIuniWmsReceiveForWL(Map<String, Object> queryParams);

	Integer selectIuniWmsReceiveForWlCount(Map<String, Object> queryParams2);

	List<Map<String, Object>> selectIuniWmsReceiveForWl2Excel(
			Map<String, Object> queryParams2);

	List<Map<String, Object>> selectIuniWmsBackGoodsForWlByPage(
			Map<String, Object> queryParams);

	Integer selectIuniWmsBackGoodsForWlCount(Map<String, Object> queryParams);

	List<Map<String, Object>> selectIuniWmsBackGoodsForWl2Excel(
			Map<String, Object> queryParams);

	List<Map<String, Object>> selectIuniWmsProcurementDetail2Excel(
			Map<String, Object> params);

	Integer selectIuniWmsProcurementDetailCount(Map<String, Object> params);

	List<Map<String, Object>> selectIuniWmsProcurementDetailByPage(
			Map<String, Object> params);


}
