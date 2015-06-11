package com.iuni.dp.service.datastat.service.wmswl.impl;

import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsStockForWlDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsStockForWlService;
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
@Service("iuniWmsStockForWlService")
public class IuniWmsStockForWlServiceImpl implements IuniWmsStockForWlService {

    private static final Logger logger = LoggerFactory.getLogger(IuniWmsStockForWlServiceImpl.class);

    @Autowired
    private IuniWmsStockForWlDao iuniWmsStockForWlDao;

    @Override
    public List<Map<String, Object>> querySumWmsStockForWlByPage(Map<String, Object> params) {
        List<Map<String, Object>> list;

        try {
            list = iuniWmsStockForWlDao.selectSumWmsStockForWlByPage(params);
            logger.debug("IuniWmsStockForWlServiceImpl.querySumWmsStockForWlByPage invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWmsStockForWlServiceImpl.querySumWmsStockForWlByPage found DataAccessException", daex);
            throw new DBException(daex);
        } catch (Exception e){
            logger.error("IuniWmsStockForWlServiceImpl.querySumWmsStockForWlByPage found Exception", e);
            throw new DBException(e);
        }
        if(list == null)
            list = new ArrayList<Map<String, Object>>();

        return list;
    }

    @Override
    public Integer querySumWmsStockForWlCount(Map<String, Object> params) {
        Integer totalCount = 0;

        try {
            totalCount = iuniWmsStockForWlDao.selectSumWmsStockForWlCount(params);
            logger.debug("IuniWmsStockForWlServiceImpl.querySumWmsStockForWlCount invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWmsStockForWlServiceImpl.querySumWmsStockForWlCount found DataAccessException", daex);
            throw new DBException(daex);
        }

        return totalCount;
    }

    @Override
    public List<Map<String, Object>> queryWmsStockForWlByPage(Map<String, Object> params) {
        List<Map<String, Object>> list;

        try {
            list = iuniWmsStockForWlDao.selectWmsStockForWlByPage(params);
            logger.debug("IuniWmsStockForWlServiceImpl.queryWmsStockForWlByPage invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWmsStockForWlServiceImpl.queryWmsStockForWlByPage found DataAccessException", daex);
            throw new DBException(daex);
        }
        if(list == null)
            list = new ArrayList<Map<String, Object>>();

        return list;
    }

    @Override
    public Integer queryWmsStockForWlCount(Map<String, Object> params) {
        Integer totalCount;

        try {
            totalCount = iuniWmsStockForWlDao.selectWmsStockForWlCount(params);
            logger.debug("IuniWmsStockForWlServiceImpl.queryWmsStockForWlCount invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWmsStockForWlServiceImpl.queryWmsStockForWlCount found DataAccessException", daex);
            throw new DBException(daex);
        }

        return totalCount;
    }
}
