package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.IuniReturnGoodsInfoDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.IuniReturnGoodsInfoService;

/**
 * IuniReturnGoodsInfo Service
 * 
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.5
 */
@Service("iuniReturnGoodsInfoService")
public class IuniReturnGoodsInfoServiceImpl implements
		IuniReturnGoodsInfoService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IuniReturnGoodsInfoDao iuniReturnGoodsInfoDao;

	@Override
	public List<Map<String, Object>> queryIuniOrderRefundDetailsByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;

		try {
			list = iuniReturnGoodsInfoDao
					.selectIuniOrderRefundDetailsByExample(params);
			logger.debug("IuniReturnGoodsInfoServiceImpl.queryIuniOrderRefundDetailsByExample invoke success");

		} catch (DataAccessException daex) {
			logger.error(
					"IuniReturnGoodsInfoServiceImpl.queryIuniOrderRefundDetailsByExample found DataAccessException",
					daex);
			throw new DBException(daex);
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniOrderRefundDetailsByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;

		try {
			list = iuniReturnGoodsInfoDao
					.selectIuniOrderRefundDetailsByPage(params);
			logger.debug("IuniReturnGoodsInfoServiceImpl.queryIuniOrderRefundDetailsByPage invoke success");

		} catch (DataAccessException daex) {
			logger.error(
					"IuniReturnGoodsInfoServiceImpl.queryIuniOrderRefundDetailsByPage found DataAccessException",
					daex);
			throw new DBException(daex);
		}

		return list;
	}

	@Override
	public Integer queryIuniOrderRefundDetailsCount(Map<String, Object> params) {
		Integer totalCount = 0;

		try {
			totalCount = iuniReturnGoodsInfoDao
					.selectIuniOrderRefundDetailsCount(params);
			logger.debug("IuniReturnGoodsInfoServiceImpl.queryIuniOrderRefundDetailsCount invoke success");

		} catch (DataAccessException daex) {
			logger.error(
					"IuniReturnGoodsInfoServiceImpl.queryIuniOrderRefundDetailsCount found DataAccessException",
					daex);
			throw new DBException(daex);
		}

		return totalCount;
	}

}
