/*
 * @(#)WmsOrderGoodsServiceImpl.java 2013-12-23
 *
 * Copyright 2013 Shenzhen Gionee,Inc. All rights reserved.
 */
package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.WmsOrderGoodsDao;
import com.iuni.dp.persist.datastat.model.WmsOrderGoods;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.WmsOrderGoodsService;

/**
 *
 * @author ZuoChangjun 2013-12-23
 */
@Service("wmsOrderGoodsService")
public class WmsOrderGoodsServiceImpl implements WmsOrderGoodsService {
	@Autowired
	private WmsOrderGoodsDao wmsOrderGoodsDao;
	/**
	 * wms销售数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<WmsOrderGoods> queryWmsOrderGoodsList(Map<String, Object> paramsMap) throws ServiceException{
		return wmsOrderGoodsDao.queryWmsOrderGoodsList(paramsMap);
	}

	/**
	 * wms销售数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryWmsOrderGoodsListCount(Map<String, Object> paramsMap)throws ServiceException{
		String endDate = (String) paramsMap.get("endDate");
		String jhEndDate = (String) paramsMap.get("jhEndDate");
		if(StringUtils.isNotBlank(endDate)){
			endDate = endDate + " 23:59:59";
		}else{
			endDate = null;
		}
		if(StringUtils.isNotBlank(jhEndDate)){
			jhEndDate = jhEndDate + " 23:59:59";
		}else{
			jhEndDate = null;
		}
		paramsMap.put("endDate", endDate);
		paramsMap.put("jhEndDate", jhEndDate);
		return wmsOrderGoodsDao.queryWmsOrderGoodsListCount(paramsMap);
	}
}
