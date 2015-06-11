package com.iuni.dp.persist.datastat.dao.wmswl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;

public interface IuniWmsOrderEffectiveTimeDao extends BaseDao<Object, Serializable>{
	
	
	public List<Map<String,Object>> selectIuniWmsOrderEffectiveWlByPage(Map<String, Object> queryParams);
	
	public Integer selectIuniWmsOrderEffectiveWlCount(Map<String, Object> queryParams);
	
	public List<Map<String,Object>> selectIuniWmsOrderEffectiveWl2Excel(Map<String, Object> queryParams);
}
