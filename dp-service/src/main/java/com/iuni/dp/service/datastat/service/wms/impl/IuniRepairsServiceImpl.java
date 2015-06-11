package com.iuni.dp.service.datastat.service.wms.impl;

import com.iuni.dp.persist.datastat.dao.wms.IuniRepairsDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.wms.IuniRepairsService;
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
@Service("iuniRepairsService")
public class IuniRepairsServiceImpl implements IuniRepairsService {

    private static final Logger logger = LoggerFactory.getLogger(IuniRepairsServiceImpl.class);

    @Autowired
    private IuniRepairsDao iuniRepairsDao;

    @Override
    public List<Map<String, Object>> queryNotInWarrantyRepairsForWlByPage(Map<String, Object> params) {
        List<Map<String, Object>> list;

        try {
            list = iuniRepairsDao.selectNotInWarrantyRepairsForWlByPage(params);
            logger.debug("IuniRepairsServiceImpl.queryNotInWarrantyRepairsForWlByPage invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniRepairsServiceImpl.queryNotInWarrantyRepairsForWlByPage found DataAccessException", daex);
            throw new DBException(daex);
        } catch (Exception e){
            logger.error("IuniRepairsServiceImpl.queryNotInWarrantyRepairsForWlByPage found Exception", e);
            throw new DBException(e);
        }
        if(list == null)
            list = new ArrayList<Map<String, Object>>();

        return list;
    }

    @Override
    public Integer queryNotInWarrantyRepairsCount(Map<String, Object> params) {
        Integer totalCount;

        try {
            totalCount = iuniRepairsDao.selectNotInWarrantyRepairsCount(params);
            logger.debug("IuniRepairsServiceImpl.queryNotInWarrantyRepairsCount invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniRepairsServiceImpl.queryNotInWarrantyRepairsCount found DataAccessException", daex);
            throw new DBException(daex);
        }

        return totalCount;
    }
}
