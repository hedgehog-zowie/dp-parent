package com.iuni.dp.service.datastat.service.wmswl.impl;

import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsFreightForWlDao;
import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsReverseSignForWlDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsFreightForWlService;
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
@Service("iuniWmsFreightForWlService")
public class IuniWmsFreightForWlServiceImpl implements IuniWmsFreightForWlService {

    private static final Logger logger = LoggerFactory.getLogger(IuniWmsFreightForWlServiceImpl.class);

    @Autowired
    private IuniWmsFreightForWlDao freightForWlDao;

    @Override
    public List<Map<String, Object>> queryWmsFreightForWlByPage(Map<String, Object> params) {
        List<Map<String, Object>> list;

        try {
            list = freightForWlDao.selectWmsFreightByPage(params);
            logger.debug("IuniWmsFreightForWlServiceImpl.queryWmsFreightForWlByPage invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWmsFreightForWlServiceImpl.queryWmsFreightForWlByPage found DataAccessException", daex);
            throw new DBException(daex);
        } catch (Exception e){
            logger.error("IuniWmsFreightForWlServiceImpl.queryWmsFreightForWlByPage found Exception", e);
            throw new DBException(e);
        }
        if(list == null)
            list = new ArrayList<Map<String, Object>>();

        return list;
    }

    @Override
    public Integer queryWmsFreightForWlCount(Map<String, Object> params) {
        Integer totalCount = 0;

        try {
            totalCount = freightForWlDao.selectWmsFreightCount(params);
            logger.debug("IuniWmsFreightForWlServiceImpl.queryWmsFreightForWlCount invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWmsFreightForWlServiceImpl.queryWmsFreightForWlCount found DataAccessException", daex);
            throw new DBException(daex);
        }

        return totalCount;
    }

}
