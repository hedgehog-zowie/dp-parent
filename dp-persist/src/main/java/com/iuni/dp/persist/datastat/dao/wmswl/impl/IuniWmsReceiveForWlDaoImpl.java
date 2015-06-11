package com.iuni.dp.persist.datastat.dao.wmswl.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.model.IuniWmsReceive;
import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsReceiveDao;

@Repository("iuniWmsReceiveDao")
public class IuniWmsReceiveForWlDaoImpl  extends BaseDaoImpl<IuniWmsReceive , Serializable> implements IuniWmsReceiveDao{

	@Override
	public List<Map<String, Object>> selectIuniWmsReceiveForWL(Map<String, Object> queryParams){
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsReceive.class.getSimpleName()+".selectIuniWmsReceiveForWL", queryParams);
		return list;
	}

	@Override
	public Integer selectIuniWmsReceiveForWlCount(
			Map<String, Object> queryParams2) {
		return findAllObjectsCount(IuniWmsReceive.class.getSimpleName()+".selectIuniWmsReceiveForWlCount", queryParams2);
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsReceiveForWl2Excel(
			Map<String, Object> queryParams2) {
		return findAllObjectsByPage2(IuniWmsReceive.class.getSimpleName()+".selectIuniWmsReceiveForWl2Excel", queryParams2);
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsBackGoodsForWlByPage(
			Map<String, Object> queryParams) {
		return findAllObjectsByPage2(IuniWmsReceive.class.getSimpleName()+".selectIuniWmsBackGoodsForWlByPage", queryParams);
	}

	@Override
	public Integer selectIuniWmsBackGoodsForWlCount(
			Map<String, Object> queryParams) {
		return findAllObjectsCount(IuniWmsReceive.class.getSimpleName()+".selectIuniWmsBackGoodsForWlCount", queryParams);
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsBackGoodsForWl2Excel(
			Map<String, Object> queryParams) {
		return findAllObjectsByPage2(IuniWmsReceive.class.getSimpleName()+".selectIuniWmsBackGoodsForWl2Excel", queryParams);
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsProcurementDetail2Excel(
			Map<String, Object> params) {
		return findAllObjectsByPage2(IuniWmsReceive.class.getSimpleName()+".selectIuniWmsProcurementDetail2Excel", params);
		
	}

	@Override
	public Integer selectIuniWmsProcurementDetailCount(
			Map<String, Object> params) {
		return findAllObjectsCount(IuniWmsReceive.class.getSimpleName()+".selectIuniWmsProcurementDetailCount", params);
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsProcurementDetailByPage(
			Map<String, Object> params) {
		return findAllObjectsByPage2(IuniWmsReceive.class.getSimpleName()+".selectIuniWmsProcurementDetailByPage", params);
	}

}
