package com.iuni.dp.persist.datastat.dao.wmswl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;

public interface IuniWmsStockMoveForWLDao extends BaseDao<Object, Serializable>{
	
	
	public List<Map<String, Object>>  selectIuniWmsStockMoveForWlByPage(Map<String, Object> queryParams);

	public Integer selectIuniWmsStockMoveForWLCount(
			Map<String, Object> queryParams);

	public List<Map<String, Object>> selectIuniWmsStockMoveForWl2Excel(
			Map<String, Object> queryParams);
}
