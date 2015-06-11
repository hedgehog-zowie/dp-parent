package com.iuni.dp.service.datastat.service.wmswl.impl;

import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsStockChannelForWlDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsStockChannelForWlService;
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
@Service("iuniWmsStockChannelForWlService")
public class IuniWmsStockChannelForWlServiceImpl implements IuniWmsStockChannelForWlService {

    private static final Logger logger = LoggerFactory.getLogger(IuniWmsStockChannelForWlServiceImpl.class);

    @Autowired
    private IuniWmsStockChannelForWlDao iuniWmsStockChannelForWlDao;

    @Override
    public List<Map<String, Object>> queryWmsStockChannelForWlByPage(Map<String, Object> params) {
        List<Map<String, Object>> list;

        try {
            list = iuniWmsStockChannelForWlDao.selectWmsStockChannelForWlByPage(params);
            logger.debug("IuniWmsStockChannelForWlServiceImpl.queryWmsStockChannelForWlByPage invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWmsStockChannelForWlServiceImpl.queryWmsStockChannelForWlByPage found DataAccessException", daex);
            throw new DBException(daex);
        } catch (Exception e){
            logger.error("IuniWmsStockChannelForWlServiceImpl.queryWmsStockChannelForWlByPage found Exception", e);
            throw new DBException(e);
        }
        if(list == null)
            list = new ArrayList<Map<String, Object>>();

        return list;
    }

    @Override
    public Integer queryWmsStockChannelForWlCount(Map<String, Object> params) {
        Integer totalCount;

        try {
            totalCount = iuniWmsStockChannelForWlDao.selectWmsStockChannelForWlCount(params);
            logger.debug("IuniWmsStockForWlServiceImpl.queryWmsStockForWlCount invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWmsStockForWlServiceImpl.queryWmsStockForWlCount found DataAccessException", daex);
            throw new DBException(daex);
        }

        return totalCount;
    }
}
