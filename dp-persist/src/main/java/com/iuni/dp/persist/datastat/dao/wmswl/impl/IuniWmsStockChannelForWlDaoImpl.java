package com.iuni.dp.persist.datastat.dao.wmswl.impl;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsStockChannelForWlDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Repository("iuniWmsStockChannelForWlDao")
public class IuniWmsStockChannelForWlDaoImpl extends BaseDaoImpl<Object, Serializable> implements IuniWmsStockChannelForWlDao {

    private static final Logger logger = LoggerFactory.getLogger(IuniWmsStockChannelForWlDaoImpl.class);

    private static final String SQL_MAP_NAMESPACE = "IuniWmsStockChannelForWl";
    private static final String SQL_MAP_METHOD_STOCK_CHANNEL_BY_PAGE = "selectWmsStockChannelForWlByPage";
    private static final String SQL_MAP_METHOD_STOCK_CHANNEL_COUNT = "selectWmsStockChannelForWlCount";

    @Override
    public List<Map<String, Object>> selectWmsStockChannelForWlByPage(Map<String, Object> params) {
        logger.debug("IuniWmsStockChannelForWlDaoImpl.selectWmsStockChannelForWlByPage(Map<String, Object> params) invoke");

        Long startTime = System.currentTimeMillis();

        List<Map<String, Object>> list;
        try {
            list = findAllObjectsByPage2(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_STOCK_CHANNEL_BY_PAGE, params);
        } catch (Exception e) {
            logger.error("IuniWmsStockChannelForWlDaoImpl.selectWmsStockChannelForWlByPage(Map<String, Object> params) failed, error msg:" + e.getMessage());
            list = new ArrayList<Map<String, Object>>();
        }

        logger.debug("IuniWmsStockChannelForWlDaoImpl.selectWmsStockChannelForWlByPage(Map<String, Object> params) success: costTime={}ms",
                new Object[]{System.currentTimeMillis() - startTime});
        return list;
    }

    @Override
    public Integer selectWmsStockChannelForWlCount(Map<String, Object> params) {
        logger.debug("IuniWmsStockChannelForWlDaoImpl.selectWmsStockChannelForWlCount(Map<String, Object>) invoke");
        Long startTime = System.currentTimeMillis();

        Integer count;
        try {
            count = findAllObjectsCount(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_STOCK_CHANNEL_COUNT, params);
        }catch (Exception e){
            logger.error("IuniWmsStockChannelForWlDaoImpl.selectWmsStockChannelForWlCount(Map<String, Object> params) failed, error msg:" + e.getMessage());
            count = 0;
        }

        logger.debug("IuniWmsStockChannelForWlDaoImpl.selectWmsStockChannelForWlCount(Map<String, Object>) success: costTime={}ms",
                new Object[]{System.currentTimeMillis() - startTime});
        return count;
    }
}
