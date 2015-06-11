package com.iuni.dp.persist.datastat.dao.wmswl.impl;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsStockSourceForWlDao;
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
@Repository("iuniWmsStockSourceForWlDao")
public class IuniWmsStockSourceForWlDaoImpl extends BaseDaoImpl<Object, Serializable> implements IuniWmsStockSourceForWlDao {

    private static final Logger logger = LoggerFactory.getLogger(IuniWmsStockSourceForWlDaoImpl.class);

    private static final String SQL_MAP_NAMESPACE = "IuniWmsStockSourceForWl";
    private static final String SQL_MAP_METHOD_STOCK_SOURCE_SUM_BY_PAGE = "selectSumWmsStockSourceForWlByPage";
    private static final String SQL_MAP_METHOD_STOCK_SOURCE_SUM_COUNT = "selectSumWmsStockSourceForWlCount";
    private static final String SQL_MAP_METHOD_STOCK_SOURCE_DETAIL_BY_PAGE = "selectWmsStockSourceForWlByPage";
    private static final String SQL_MAP_METHOD_STOCK_SOURCE_DETAIL_COUNT = "selectWmsStockSourceForWlCount";

    @Override
    public List<Map<String, Object>> selectSumWmsStockSourceForWlByPage(Map<String, Object> params) {
        logger.debug("IuniWmsStockSourceForWlDaoImpl.selectSumWmsStockForWlByPage(Map<String, Object> params) invoke");

        Long startTime = System.currentTimeMillis();

        List<Map<String, Object>> list;
        try {
            list = findAllObjectsByPage2(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_STOCK_SOURCE_SUM_BY_PAGE, params);
        }catch(Exception e){
            logger.error("IuniWmsStockSourceForWlDaoImpl.selectSumWmsStockSourceForWlByPage(Map<String, Object> params) failed, error msg:" + e.getMessage());
            list = new ArrayList<Map<String, Object>>();
        }

        logger.debug("IuniWmsStockSourceForWlDaoImpl.selectSumWmsStockSourceForWlByPage(Map<String, Object> params) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - startTime });
        return list;
    }

    @Override
    public Integer selectSumWmsStockSourceForWlCount(Map<String, Object> params) {
        logger.debug("IuniWmsStockSourceForWlDaoImpl.selectSumWmsStockSourceForWlCount(Map<String, Object>) invoke");
        Long startTime = System.currentTimeMillis();

        Integer count;
        try {
            count = findAllObjectsCount(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_STOCK_SOURCE_SUM_COUNT, params);
        }catch(Exception e){
            logger.error("IuniWmsStockForWlDaoImpl.selectSumWmsStockSourceForWlCount(Map<String, Object> params) failed, error msg:" + e.getMessage());
            count = 0;
        }

        logger.debug("IuniWmsStockSourceForWlDaoImpl.selectSumWmsStockSourceForWlCount(Map<String, Object>) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - startTime });
        return count;
    }

    @Override
    public List<Map<String, Object>> selectWmsStockSourceForWlByPage(Map<String, Object> params) {
        logger.debug("IuniWmsStockSourceForWlDaoImpl.selectWmsStockSourcForWlByPage(Map<String, Object> params) invoke");

        Long startTime = System.currentTimeMillis();

        List<Map<String, Object>> list;
        try {
            list = findAllObjectsByPage2(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_STOCK_SOURCE_DETAIL_BY_PAGE, params);
        }catch(Exception e){
            logger.error("IuniWmsStockSourceForWlDaoImpl.selectWmsStockSourceForWlByPage(Map<String, Object> params) failed, error msg:" + e.getMessage());
            list = new ArrayList<Map<String, Object>>();
        }

        logger.debug("IuniWmsStockSourceForWlDaoImpl.selectWmsStockSourceForWlByPage(Map<String, Object> params) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - startTime });
        return list;
    }

    @Override
    public Integer selectWmsStockSourceForWlCount(Map<String, Object> params) {
        logger.debug("IuniWmsStockSourceForWlDaoImpl.selectWmsStockSourceForWlCount(Map<String, Object>) invoke");
        Long startTime = System.currentTimeMillis();
        Integer count;
        try {
            count = findAllObjectsCount(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_STOCK_SOURCE_DETAIL_COUNT, params);
        }catch(Exception e){
            logger.error("IuniWmsStockForWlDaoImpl.selectWmsStockSourceForWlCount(Map<String, Object> params) failed, error msg:" + e.getMessage());
            count = 0;
        }

        logger.debug("IuniWmsStockSourceForWlDaoImpl.selectWmsStockSourceForWlCount(Map<String, Object>) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - startTime });
        return count;
    }
}
