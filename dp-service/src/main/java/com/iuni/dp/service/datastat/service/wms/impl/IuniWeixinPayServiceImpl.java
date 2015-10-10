package com.iuni.dp.service.datastat.service.wms.impl;

import com.iuni.dp.persist.datastat.dao.wms.IuniWeixinPayDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.wms.IuniWeixinPayService;
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
@Service("iuniWeixinPayService")
public class IuniWeixinPayServiceImpl implements IuniWeixinPayService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IuniWeixinPayDao iuniWeixinPayDao;

    @Override
    public List<Map<String, Object>> queryIuniWeixinPayByPage(Map<String, Object> params) {
        List<Map<String, Object>> list;

        try {
            list = iuniWeixinPayDao.selectIuniWeixinPayByPage(params);
            logger.debug("IuniWeixinPayServiceImpl.queryIuniWeixinPayByPage invoke success");

        } catch(DataAccessException daex) {
            logger.error("IuniWeixinPayServiceImpl.queryIuniWeixinPayByPage found DataAccessException", daex);
            throw new DBException(daex);
        }

        return list;
    }

    @Override
    public Integer queryIuniWeixinPayCount(Map<String, Object> params) {
        Integer totalCount;

        try {
            totalCount = iuniWeixinPayDao.selectIuniWeixinPayCount(params);
            logger.debug("IuniWeixinPayServiceImpl.queryIuniWeixinPayCount invoke success");

        } catch(DataAccessException daex) {
            logger.error("IuniWeixinPayServiceImpl.queryIuniWeixinPayCount found DataAccessException", daex);
            throw new DBException(daex);
        }

        return totalCount;
    }
}
