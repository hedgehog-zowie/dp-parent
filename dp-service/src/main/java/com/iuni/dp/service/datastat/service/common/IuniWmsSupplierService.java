package com.iuni.dp.service.datastat.service.common;

import java.util.Map;

/**
 * 供应商业务接口
 * @author dan.wang@iuni.com
 *
 */
public interface IuniWmsSupplierService {
	
	/**
	 * 查询所有供应商
	 * @return
	 */
	public Map<String, String> findAllSuppliers();
}
