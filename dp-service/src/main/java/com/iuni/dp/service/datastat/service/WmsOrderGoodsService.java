/*
 * @(#)WmsOrderGoodsService.java 2013-12-23
 *
 * Copyright 2013 Shenzhen Gionee,Inc. All rights reserved.
 */
package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.WmsOrderGoods;
import com.iuni.dp.service.common.exception.ServiceException;

/**
 *
 * @author ZuoChangjun 2013-12-23
 */
public interface WmsOrderGoodsService {
	/**
	 * wms销售数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<WmsOrderGoods> queryWmsOrderGoodsList(Map<String, Object> paramsMap) throws ServiceException;

	/**
	 * wms销售数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryWmsOrderGoodsListCount(Map<String, Object> paramsMap)throws ServiceException;
}
