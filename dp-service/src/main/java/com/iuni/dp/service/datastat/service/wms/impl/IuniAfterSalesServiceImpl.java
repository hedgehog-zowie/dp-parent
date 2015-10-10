package com.iuni.dp.service.datastat.service.wms.impl;

import com.iuni.dp.persist.datastat.dao.wms.IuniAfterSalesDao;
import com.iuni.dp.persist.datastat.dao.wms.IuniAlipayDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.wms.IuniAfterSalesService;
import com.iuni.dp.service.datastat.service.wms.IuniAlipayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Service("iuniAfterSalesService")
public class IuniAfterSalesServiceImpl implements IuniAfterSalesService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IuniAfterSalesDao iuniAfterSalesDao;

    @Override
    public List<Map<String, Object>> queryIuniAfterSalesNumByPage(Map<String, Object> params) {
        List<Map<String, Object>> list;

        try {
            list = iuniAfterSalesDao.selectIuniAfterSalesNumByPage(params);
            logger.debug("IuniAfterSalesServiceImpl.queryIuniAfterSalesNumByPage invoke success");

        } catch(DataAccessException daex) {
            logger.error("IuniAfterSalesServiceImpl.queryIuniAfterSalesNumByPage found DataAccessException", daex);
            throw new DBException(daex);
        }

        return list;
    }

    @Override
    public Integer queryIuniAfterSalesNumCount(Map<String, Object> params) {
        Integer totalCount;

        try {
            totalCount = iuniAfterSalesDao.selectIuniAfterSalesNumCount(params);
            logger.debug("IuniAfterSalesServiceImpl.queryIuniAfterSalesNumCount invoke success");

        } catch(DataAccessException daex) {
            logger.error("IuniAfterSalesServiceImpl.queryIuniAfterSalesNumCount found DataAccessException", daex);
            throw new DBException(daex);
        }

        return totalCount;
    }
}
