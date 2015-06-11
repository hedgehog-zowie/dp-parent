package com.iuni.dp.persist.datastat.common.dao;

import java.util.List;

import com.iuni.dp.persist.datastat.common.model.IuniWmsSupplier;

/**
 * 
 * @author dan.wang@iuni.com
 *
 */
public interface IuniWmsSupplierDAO {
	/**
	 * 查询所有供应商
	 * @return
	 */
	List<IuniWmsSupplier> selectAllSuppliers();
   
}