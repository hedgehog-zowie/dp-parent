package com.iuni.dp.service.datastat.service.wms.impl;

import com.iuni.dp.persist.datastat.dao.wms.IuniAlipayDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.wms.IuniAlipayService;
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
@Service("iuniAlipayService")
public class IuniAlipayServiceImpl implements IuniAlipayService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IuniAlipayDao iuniAlipayDao;

    @Override
    public List<Map<String, Object>> queryIuniAlipayByPage(Map<String, Object> params) {
        List<Map<String, Object>> list;

        try {
            list = iuniAlipayDao.selectIuniAlipayByPage(params);
            logger.debug("IuniAlipayServiceImpl.queryIuniAlipayByPage invoke success");

        } catch(DataAccessException daex) {
            logger.error("IuniAlipayServiceImpl.queryIuniAlipayByPage found DataAccessException", daex);
            throw new DBException(daex);
        }

        return list;
    }

    @Override
    public Integer queryIuniAlipayCount(Map<String, Object> params) {
        Integer totalCount;

        try {
            totalCount = iuniAlipayDao.selectIuniAlipayCount(params);
            logger.debug("IuniAlipayServiceImpl.queryIuniAlipayCount invoke success");

        } catch(DataAccessException daex) {
            logger.error("IuniAlipayServiceImpl.queryIuniAlipayCount found DataAccessException", daex);
            throw new DBException(daex);
        }

        return totalCount;
    }
}
