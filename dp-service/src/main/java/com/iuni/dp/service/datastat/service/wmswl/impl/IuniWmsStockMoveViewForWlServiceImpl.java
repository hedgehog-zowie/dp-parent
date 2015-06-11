package com.iuni.dp.service.datastat.service.wmswl.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsStockMoveForWLDao;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsStockMoveViewForWlService;



@Service("iuniWmsStockMoveViewForWlService")
public class IuniWmsStockMoveViewForWlServiceImpl implements IuniWmsStockMoveViewForWlService {
	
	@Autowired
	private IuniWmsStockMoveForWLDao iuniWmsStockMoveForWLDao;
	
	@Override
	public List<Map<String, Object>> queryIuniWmsStockMoveForWl(
			Map<String, Object> queryParams) {
		List<Map<String, Object>> list = iuniWmsStockMoveForWLDao.selectIuniWmsStockMoveForWlByPage(queryParams);
		return list;
	}

	@Override
	public Integer queryIuniWmsStockMoveForWlCount(
			Map<String, Object> queryParams) {
		// TODO Auto-generated method stub
		return iuniWmsStockMoveForWLDao.selectIuniWmsStockMoveForWLCount(queryParams);
	}

	@Override
	public List<Map<String, Object>> queryIuniWmsStockMoveForWl2Excel(
			Map<String, Object> queryParams) {
		// TODO Auto-generated method stub
		return iuniWmsStockMoveForWLDao.selectIuniWmsStockMoveForWl2Excel(queryParams);
	}

}
