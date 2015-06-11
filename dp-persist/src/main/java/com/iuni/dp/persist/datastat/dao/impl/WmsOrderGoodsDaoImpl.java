/*
 * @(#)WmsOrderGoodsImpl.java 2013-12-23
 *
 * Copyright 2013 Shenzhen Gionee,Inc. All rights reserved.
 */
package com.iuni.dp.persist.datastat.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.datastat.dao.WmsOrderGoodsDao;
import com.iuni.dp.persist.datastat.model.WmsOrderGoods;

/**
 * 
 * @author ZuoChangjun 2013-12-23
 */
@Repository("wmsOrderGoodsDao")
public class WmsOrderGoodsDaoImpl implements WmsOrderGoodsDao {
	@Autowired
	@Qualifier("sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	/**
	 * wms销售数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<WmsOrderGoods> queryWmsOrderGoodsList(
			Map<String, Object> paramsMap) {
		return sqlMapClientTemplate.queryForList(
				"WmsOrderGoods.queryWmsOrderGoodsList", paramsMap);
	}

	/**
	 * wms销售数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryWmsOrderGoodsListCount(Map<String, Object> paramsMap) {
		return (Integer) sqlMapClientTemplate.queryForObject(
				"WmsOrderGoods.queryWmsOrderGoodsListCount", paramsMap);
	}
}
