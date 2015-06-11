package com.iuni.dp.service.datastat.service.common;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.common.model.IuniWmsSku;

/**
 * 
 * @author dan.wang@iuni.com
 *
 */
public interface IuniWmsSkuService {
	
	/**
	 * 查询商品
	 */
	public List<IuniWmsSku> queryIuniWmsSku(String goodsType);



}
