package com.iuni.dp.persist.datastat.dao.wmswl.impl;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsStockForWlDao;
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
@Repository("iuniWmsStockForWlDao")
public class IuniWmsStockForWlDaoImpl extends BaseDaoImpl<Object, Serializable> implements IuniWmsStockForWlDao{

    private static final Logger logger = LoggerFactory.getLogger(IuniWmsStockForWlDaoImpl.class);

    private static final String SQL_MAP_NAMESPACE = "IuniWmsStockForWl";
    private static final String SQL_MAP_METHOD_STOCK_SUM_BY_PAGE = "selectSumWmsStockForWlByPage";
    private static final String SQL_MAP_METHOD_STOCK_SUM_COUNT = "selectSumWmsStockForWlCount";
    private static final String SQL_MAP_METHOD_STOCK_DETAIL_BY_PAGE = "selectWmsStockForWlByPage";
    private static final String SQL_MAP_METHOD_STOCK_DETAIL_COUNT = "selectWmsStockForWlCount";

    @Override
    public List<Map<String, Object>> selectSumWmsStockForWlByPage(Map<String, Object> params) {
        logger.debug("IuniWmsStockForWlDaoImpl.selectSumWmsStockForWlByPage(Map<String, Object> params) invoke");

        Long startTime = System.currentTimeMillis();

        List<Map<String, Object>> list ;
        try {
            list = findAllObjectsByPage2(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_STOCK_SUM_BY_PAGE, params);
        }catch (Exception e){
            logger.error("IuniWmsStockForWlDaoImpl.selectSumWmsStockForWlByPage(Map<String, Object> params) failed, error msg:" + e.getMessage());
            list = new ArrayList<Map<String, Object>>();
        }

        logger.debug("IuniWmsStockForWlDaoImpl.selectSumWmsStockForWlByPage(Map<String, Object> params) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - startTime });
        return list;
    }

    @Override
    public Integer selectSumWmsStockForWlCount(Map<String, Object> params) {
        logger.debug("IuniWmsStockForWlDaoImpl.selectSumWmsStockForWlCount(Map<String, Object>) invoke");
        Long startTime = System.currentTimeMillis();

        Integer count;
        try {
            count = findAllObjectsCount(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_STOCK_SUM_COUNT, params);
        }catch(Exception e){
            logger.error("IuniWmsStockForWlDaoImpl.selectSumWmsStockForWlCount(Map<String, Object> params) failed, error msg:" + e.getMessage());
            count = 0;
        }

        logger.debug("IuniWmsStockForWlDaoImpl.selectSumWmsStockForWlCount(Map<String, Object>) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - startTime });
        return count;
    }

    @Override
    public List<Map<String, Object>> selectWmsStockForWlByPage(Map<String, Object> params) {
        logger.debug("IuniWmsStockForWlDaoImpl.selectWmsStockForWlByPage(Map<String, Object> params) invoke");

        Long startTime = System.currentTimeMillis();

        List<Map<String, Object>> list;
        try {
            list = findAllObjectsByPage2(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_STOCK_DETAIL_BY_PAGE, params);
        }catch(Exception e){
            logger.error("IuniWmsStockForWlDaoImpl.selectWmsStockForWlByPage(Map<String, Object> params) failed, error msg:" + e.getMessage());
            list = new ArrayList<Map<String, Object>>();
        }

        logger.debug("IuniWmsStockForWlDaoImpl.selectWmsStockForWlByPage(Map<String, Object> params) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - startTime });
        return list;
    }

    @Override
    public Integer selectWmsStockForWlCount(Map<String, Object> params) {
        logger.debug("IuniWmsStockForWlDaoImpl.selectWmsStockForWlCount(Map<String, Object>) invoke");
        Long startTime = System.currentTimeMillis();

        Integer count ;
        try {
            count = findAllObjectsCount(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_STOCK_DETAIL_COUNT, params);
        }catch(Exception e){
            logger.error("IuniWmsStockForWlDaoImpl.selectWmsStockForWlCount(Map<String, Object> params) failed, error msg:" + e.getMessage());
            count = 0;
        }

        logger.debug("IuniWmsStockForWlDaoImpl.selectWmsStockForWlCount(Map<String, Object>) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - startTime });
        return count;
    }

}
