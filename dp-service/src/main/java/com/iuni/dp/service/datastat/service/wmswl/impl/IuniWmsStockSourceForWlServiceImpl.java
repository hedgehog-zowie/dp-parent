package com.iuni.dp.service.datastat.service.wmswl.impl;

import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsStockSourceForWlDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsStockSourceForWlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Service("iuniWmsStockSourceForWlService")
public class IuniWmsStockSourceForWlServiceImpl implements IuniWmsStockSourceForWlService {

    private static final Logger logger = LoggerFactory.getLogger(IuniWmsStockSourceForWlServiceImpl.class);

    @Autowired
    private IuniWmsStockSourceForWlDao iuniWmsStockSourceForWlDao;

    @Override
    public List<Map<String, Object>> querySumWmsStockSourceForWlByPage(Map<String, Object> params) {
        List<Map<String, Object>> list;

        try {
            list = iuniWmsStockSourceForWlDao.selectSumWmsStockSourceForWlByPage(params);
            logger.debug("IuniWmsStockSourceForWlServiceImpl.querySumWmsStockSourceForWlByPage invoke success");
        } catch (DataAccessException daex) {
            logger.error("IuniWmsStockSourceForWlServiceImpl.querySumWmsStockSourceForWlByPage found DataAccessException", daex);
            throw new DBException(daex);
        } catch (Exception e) {
            logger.error("IuniWmsStockSourceForWlServiceImpl.selectSumWmsStockSourceForWlByPage found Exception", e);
            throw new DBException(e);
        }
        if (list == null)
            list = new ArrayList<Map<String, Object>>();

        return list;
    }

    @Override
    public Integer querySumWmsStockSourceForWlCount(Map<String, Object> params) {
        Integer totalCount;

        try {
            totalCount = iuniWmsStockSourceForWlDao.selectSumWmsStockSourceForWlCount(params);
            logger.debug("IuniWmsStockSourceForWlServiceImpl.querySumWmsStockSourceForWlCount invoke success");
        } catch (DataAccessException daex) {
            logger.error("IuniWmsStockSourceForWlServiceImpl.querySumWmsStockSourceForWlCount found DataAccessException", daex);
            throw new DBException(daex);
        }

        return totalCount;
    }

    @Override
    public List<Map<String, Object>> queryWmsStockSourceForWlByPage(Map<String, Object> params) {
        List<Map<String, Object>> list;

        try {
            list = iuniWmsStockSourceForWlDao.selectWmsStockSourceForWlByPage(params);
            logger.debug("IuniWmsStockSourceForWlServiceImpl.queryWmsStockSourceForWlByPage invoke success");
        } catch (DataAccessException daex) {
            logger.error("IuniWmsStockSourceForWlServiceImpl.queryWmsStockSourceForWlByPage found DataAccessException", daex);
            throw new DBException(daex);
        }
        if (list == null)
            list = new ArrayList<Map<String, Object>>();

        return list;
    }

    @Override
    public Integer queryWmsStockSourceForWlCount(Map<String, Object> params) {
        Integer totalCount;

        try {
            totalCount = iuniWmsStockSourceForWlDao.selectWmsStockSourceForWlCount(params);
            logger.debug("IuniWmsStockSourceForWlServiceImpl.queryWmsStockSourceForWlCount invoke success");
        } catch (DataAccessException daex) {
            logger.error("IuniWmsStockSourceForWlServiceImpl.queryWmsStockSourceForWlCount found DataAccessException", daex);
            throw new DBException(daex);
        }

        return totalCount;
    }
}
