package com.iuni.dp.service.datastat.service.common.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.common.dao.IuniWmsSupplierDAO;
import com.iuni.dp.persist.datastat.common.model.IuniWmsSupplier;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.common.IuniWmsSupplierService;

@Service("iuniIuniWmsSupplierService")
public class IuniWmsSupplierServiceImpl implements
		IuniWmsSupplierService {
	 private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private IuniWmsSupplierDAO iuniWmsSupplierDAO;
	
	@Override
	public Map<String, String> findAllSuppliers() {
		Map<String, String> allSuppliers = new LinkedHashMap<String, String>();
		
		try {
			List<IuniWmsSupplier> list = iuniWmsSupplierDAO.selectAllSuppliers();
			for(IuniWmsSupplier e : list){
				allSuppliers.put(e.getId(), e.getSupplierName());
			}
			
			logger.debug("IuniIuniWmsSupplierServiceImpl.findAllSuppliers invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniIuniWmsSupplierServiceImpl.findAllSuppliers found DataAccessException", daex);
			throw new DBException(daex);
		}
		return allSuppliers;
	}

}
