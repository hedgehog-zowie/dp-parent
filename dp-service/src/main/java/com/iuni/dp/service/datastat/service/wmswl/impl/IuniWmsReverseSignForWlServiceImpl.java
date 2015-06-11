package com.iuni.dp.service.datastat.service.wmswl.impl;

import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsReverseSignForWlDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsReverseSignForWlService;
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
@Service("iuniWmsReverseSignForWlService")
public class IuniWmsReverseSignForWlServiceImpl implements IuniWmsReverseSignForWlService {

    private static final Logger logger = LoggerFactory.getLogger(IuniWmsReverseSignForWlServiceImpl.class);

    @Autowired
    private IuniWmsReverseSignForWlDao reverseSignForWlDao;

    @Override
    public List<Map<String, Object>> queryWmsReverseSignOfBackForWlByPage(Map<String, Object> params) {
        List<Map<String, Object>> list;

        try {
            list = reverseSignForWlDao.selectWmsReverseSignOfBackByPage(params);
            logger.debug("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfBackForWlByPage invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfBackForWlByPage found DataAccessException", daex);
            throw new DBException(daex);
        } catch (Exception e){
            logger.error("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfBackForWlByPage found Exception", e);
            throw new DBException(e);
        }
        if(list == null)
            list = new ArrayList<Map<String, Object>>();

        return list;
    }

    @Override
    public Integer queryWmsReverseSignOfBackForWlCount(Map<String, Object> params) {
        Integer totalCount = 0;

        try {
            totalCount = reverseSignForWlDao.selectWmsReverseSignOfBackForWlCount(params);
            logger.debug("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfExchangeForWlCount invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfExchangeForWlCount found DataAccessException", daex);
            throw new DBException(daex);
        }

        return totalCount;
    }

    @Override
    public List<Map<String, Object>> queryWmsReverseSignOfExchangeForWlByPage(Map<String, Object> params) {
        List<Map<String, Object>> list;

        try {
            list = reverseSignForWlDao.selectWmsReverseSignOfExchangeForWlByPage(params);
            logger.debug("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfExchangeForWlByPage invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfExchangeForWlByPage found DataAccessException", daex);
            throw new DBException(daex);
        } catch (Exception e){
            logger.error("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfExchangeForWlByPage found Exception", e);
            throw new DBException(e);
        }
        if(list == null)
            list = new ArrayList<Map<String, Object>>();

        return list;
    }

    @Override
    public Integer queryWmsReverseSignOfExchangeForWlCount(Map<String, Object> params) {
        Integer totalCount = 0;

        try {
            totalCount = reverseSignForWlDao.selectWmsReverseSignOfExchangeForWlCount(params);
            logger.debug("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfExchangeForWlCount invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfExchangeForWlCount found DataAccessException", daex);
            throw new DBException(daex);
        }

        return totalCount;
    }

    @Override
    public List<Map<String, Object>> queryWmsReverseSignOfRepairForWlByPage(Map<String, Object> params) {
        List<Map<String, Object>> list;

        try {
            list = reverseSignForWlDao.selectWmsReverseSignOfRepairForWlByPage(params);
            logger.debug("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfRepairForWlByPage invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfRepairForWlByPage found DataAccessException", daex);
            throw new DBException(daex);
        } catch (Exception e){
            logger.error("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfRepairForWlByPage found Exception", e);
            throw new DBException(e);
        }
        if(list == null)
            list = new ArrayList<Map<String, Object>>();

        return list;
    }

    @Override
    public Integer queryWmsReverseSignOfRepairForWlCount(Map<String, Object> params) {
        Integer totalCount = 0;

        try {
            totalCount = reverseSignForWlDao.selectWmsReverseSignOfRepairForWlCount(params);
            logger.debug("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfRepairForWlCount invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWmsReverseSignForWlServiceImpl.queryWmsReverseSignOfRepairForWlCount found DataAccessException", daex);
            throw new DBException(daex);
        }

        return totalCount;
    }
}
