package com.iuni.dp.service.datastat.service.wmswl.impl;

import java.util.List;
import java.util.Map;

import com.iuni.dp.service.datastat.service.wmswl.IuniWmsBackGoodsViewForWlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsReceiveDao;

@Service("iuniWmsBackGoodsViewForWlService")
public class IuniWmsBackGoodsViewForWlServiceImpl implements IuniWmsBackGoodsViewForWlService {

	@Autowired
	private IuniWmsReceiveDao iuniWmsReceiveDao;
	
	@Override
	public List<Map<String, Object>> queryIuniWmsBackGoodsForWlByPage(
			Map<String, Object> queryParams) {
		return iuniWmsReceiveDao.selectIuniWmsBackGoodsForWlByPage(queryParams);
	}

	@Override
	public Integer queryIuniWmsBackGoodsForWlCount(
			Map<String, Object> queryParams) {
		return iuniWmsReceiveDao.selectIuniWmsBackGoodsForWlCount(queryParams);
	}

	@Override
	public List<Map<String, Object>> queryIuniWmsBackGoodsForWl2Excel(
			Map<String, Object> queryParams) {
		return iuniWmsReceiveDao.selectIuniWmsBackGoodsForWl2Excel(queryParams);
	}

}
