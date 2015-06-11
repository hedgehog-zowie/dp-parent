package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.IuniSalesOfGoodsDao;
import com.iuni.dp.service.datastat.service.IuniSalesOfGoodsService;

@Service("iuniSalesOfGoodsService")
public class IuniSalesOfGoodsServiceImpl implements IuniSalesOfGoodsService {
	
	@Autowired
	private IuniSalesOfGoodsDao iuniSalesOfGoodsDao;
	@Override
	public List<Map<String, Object>> queryIuniSalesOfGoodsBypage(
			Map<String, Object> paramsMap) {
		return iuniSalesOfGoodsDao.selectSalesOfGoodsByPage(paramsMap);
	}

	@Override
	public Integer queryIuniSalesOfGoodsCount(Map<String, Object> paramsMap) {
		return iuniSalesOfGoodsDao.selectSalesOfGoodsCount(paramsMap);
	}

	@Override
	public List<Map<String, Object>> queryIuniSalesOfGoods2Excel(
			Map<String, Object> paramsMap) {
		return iuniSalesOfGoodsDao.selectSalesOfGoods2Excel(paramsMap);
	}

}
