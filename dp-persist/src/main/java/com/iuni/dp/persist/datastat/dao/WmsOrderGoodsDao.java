/*
 * @(#)WmsOrderGoodsDao.java 2013-12-23
 *
 * Copyright 2013 Shenzhen Gionee,Inc. All rights reserved.
 */
package com.iuni.dp.persist.datastat.dao;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.WmsOrderGoods;

/**
 *
 * @author ZuoChangjun 2013-12-23
 */
public interface WmsOrderGoodsDao {
	/**
	 * wms销售数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<WmsOrderGoods> queryWmsOrderGoodsList(Map<String, Object> paramsMap);
		
	/**
	 * wms销售数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryWmsOrderGoodsListCount(Map<String, Object> paramsMap);
}
