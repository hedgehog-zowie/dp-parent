package com.iuni.dp.service.datastat.service.wms.impl;

import java.util.List;
import java.util.Map;

import com.iuni.dp.service.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.wms.IuniWmsSalesOrderDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.wms.IuniWmsSalesOrderService;

/**
 * IuniWmsSalesOrder Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.5
 */
@Service("iuniWmsSalesOrderService")
public class IuniWmsSalesOrderServiceImpl implements IuniWmsSalesOrderService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IuniWmsSalesOrderDao iuniWmsSalesOrderDao;

	@Override
	public List<Map<String, Object>> queryIuniWmsSalesOrderStatByExample(Map<String, Object> params) {
		List<Map<String, Object>> list = null;

		try {
			list = iuniWmsSalesOrderDao.selectIuniWmsSalesOrderStatByExample(params);
			logger.debug("IuniWmsSalesOrderServiceImpl.queryIuniWmsSalesOrderStatByExample invoke success");

		} catch(DataAccessException daex) {
			logger.error("IuniWmsSalesOrderServiceImpl.queryIuniWmsSalesOrderStatByExample found DataAccessException", daex);
			throw new DBException(daex);
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniWmsSalesOrderStatByPage(Map<String, Object> params) {
		List<Map<String, Object>> list = null;

		try {
			list = iuniWmsSalesOrderDao.selectIuniWmsSalesOrderStatByPage(params);
			logger.debug("IuniWmsSalesOrderServiceImpl.queryIuniWmsSalesOrderStatByPage invoke success");

		} catch(DataAccessException daex) {
			logger.error("IuniWmsSalesOrderServiceImpl.queryIuniWmsSalesOrderStatByPage found DataAccessException", daex);
			throw new DBException(daex);
		}

		return list;
	}

	@Override
	public Integer queryIuniWmsSalesOrderStatCount(Map<String, Object> params) {
		Integer totalCount = 0;

		try {
			totalCount = iuniWmsSalesOrderDao.selectIuniWmsSalesOrderStatCount(params);
			logger.debug("IuniWmsSalesOrderServiceImpl.queryIuniWmsSalesOrderStatCount invoke success");

		} catch(DataAccessException daex) {
			logger.error("IuniWmsSalesOrderServiceImpl.queryIuniWmsSalesOrderStatCount found DataAccessException", daex);
			throw new DBException(daex);
		}

		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIuniStockMovDetailsByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;

		try {
			list = iuniWmsSalesOrderDao.selectIuniStockMovDetailsByExample(params);
			logger.debug("IuniWmsSalesOrderServiceImpl.queryIuniStockMovDetailsByExample invoke success");

		} catch(DataAccessException daex) {
			logger.error("IuniWmsSalesOrderServiceImpl.queryIuniStockMovDetailsByExample found DataAccessException", daex);
			throw new DBException(daex);
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniStockMovDetailsByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;

		try {
			list = iuniWmsSalesOrderDao.selectIuniStockMovDetailsByPage(params);
			logger.debug("IuniWmsSalesOrderServiceImpl.queryIuniStockMovDetailsByPage invoke success");

		} catch(DataAccessException daex) {
			logger.error("IuniWmsSalesOrderServiceImpl.queryIuniStockMovDetailsByPage found DataAccessException", daex);
			throw new DBException(daex);
		}

		return list;
	}

	@Override
	public Integer queryIuniStockMovDetailsCount(Map<String, Object> params) {
		Integer totalCount = 0;

		try {
			totalCount = iuniWmsSalesOrderDao.selectIuniStockMovDetailsCount(params);
			logger.debug("IuniWmsSalesOrderServiceImpl.queryIuniStockMovDetailsCount invoke success");

		} catch(DataAccessException daex) {
			logger.error("IuniWmsSalesOrderServiceImpl.queryIuniStockMovDetailsCount found DataAccessException", daex);
			throw new DBException(daex);
		}

		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIuniNoInvoiceSalesDetailsByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;

		try {
			list = iuniWmsSalesOrderDao.selectIuniNoInvoiceSalesDetailsByExample(params);
			logger.debug("IuniWmsSalesOrderServiceImpl.queryIuniNoInvoiceSalesDetailsByExample invoke success");

		} catch(DataAccessException daex) {
			logger.error("IuniWmsSalesOrderServiceImpl.queryIuniNoInvoiceSalesDetailsByExample found DataAccessException", daex);
			throw new DBException(daex);
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniNoInvoiceSalesDetailsByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;

		try {
			list = iuniWmsSalesOrderDao.selectIuniNoInvoiceSalesDetailsByPage(params);
			logger.debug("IuniWmsSalesOrderServiceImpl.queryIuniNoInvoiceSalesDetailsByPage invoke success");

		} catch(DataAccessException daex) {
			logger.error("IuniWmsSalesOrderServiceImpl.queryIuniNoInvoiceSalesDetailsByPage found DataAccessException", daex);
			throw new DBException(daex);
		}

		return list;
	}

	@Override
	public Integer queryIuniNoInvoiceSalesDetailsCount(
			Map<String, Object> params) {
		Integer totalCount = 0;

		try {
			totalCount = iuniWmsSalesOrderDao.selectIuniNoInvoiceSalesDetailsCount(params);
			logger.debug("IuniWmsSalesOrderServiceImpl.queryIuniNoInvoiceSalesDetailsCount invoke success");

		} catch(DataAccessException daex) {
			logger.error("IuniWmsSalesOrderServiceImpl.queryIuniNoInvoiceSalesDetailsCount found DataAccessException", daex);
			throw new DBException(daex);
		}

		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIuniWmsPayAmountCheckByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;

		try {
			list = iuniWmsSalesOrderDao.selectIuniWmsPayAmountCheckByExample(params);
			logger.debug("IuniWmsSalesOrderServiceImpl.queryIuniWmsPayAmountCheckByExample invoke success");

		} catch(DataAccessException daex) {
			logger.error("IuniWmsSalesOrderServiceImpl.queryIuniWmsPayAmountCheckByExample found DataAccessException", daex);
			throw new DBException(daex);
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniWmsPayAmountCheckByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;

		try {
			list = iuniWmsSalesOrderDao.selectIuniWmsPayAmountCheckByPage(params);
			logger.debug("IuniWmsSalesOrderServiceImpl.queryIuniWmsPayAmountCheckByPage invoke success");

		} catch(DataAccessException daex) {
			logger.error("IuniWmsSalesOrderServiceImpl.queryIuniWmsPayAmountCheckByPage found DataAccessException", daex);
			throw new DBException(daex);
		}

		return list;
	}

	@Override
	public Integer queryIuniWmsPayAmountCheckCount(Map<String, Object> params) {
		Integer totalCount = 0;

		try {
			totalCount = iuniWmsSalesOrderDao.selectIuniWmsPayAmountCheckCount(params);
			logger.debug("IuniWmsSalesOrderServiceImpl.queryIuniWmsPayAmountCheckCount invoke success");

		} catch(DataAccessException daex) {
			logger.error("IuniWmsSalesOrderServiceImpl.queryIuniWmsPayAmountCheckCount found DataAccessException", daex);
			throw new DBException(daex);
		}

		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIuniRebatesDetailByPage(Map<String, Object> params) throws ServiceException {
		List<Map<String, Object>> list = null;

		try {
			list = iuniWmsSalesOrderDao.selectIuniRebatesDetailByPage(params);
			logger.debug("IuniWmsSalesOrderServiceImpl.queryIuniRebatesDetailByPage invoke success");
		} catch(DataAccessException daex) {
			logger.error("IuniWmsSalesOrderServiceImpl.queryIuniRebatesDetailByPage found DataAccessException", daex);
			throw new DBException(daex);
		}

		return list;
	}

	@Override
	public Integer queryIuniRebatesDetailCount(Map<String, Object> params) throws ServiceException {
		Integer totalCount = 0;

		try {
			totalCount = iuniWmsSalesOrderDao.selectIuniRebatesDetailCount(params);
			logger.debug("IuniWmsSalesOrderServiceImpl.queryIuniRebatesDetailCount invoke success");
		} catch(DataAccessException daex) {
			logger.error("IuniWmsSalesOrderServiceImpl.queryIuniRebatesDetailCount found DataAccessException", daex);
			throw new DBException(daex);
		}

		return totalCount;
	}

}
