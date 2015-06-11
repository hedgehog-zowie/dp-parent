package com.iuni.dp.persist.datastat.dao.wmswl.impl;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsReverseSignForWlDao;
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
@Repository("iuniWmsReverseSignForWlDao")
public class IuniWmsReverseSignForWlDaoImpl extends BaseDaoImpl<Object, Serializable> implements IuniWmsReverseSignForWlDao {

    private static final Logger logger = LoggerFactory.getLogger(IuniWmsReverseSignForWlDaoImpl.class);

    private static final String SQL_MAP_NAMESPACE = "IuniWmsReverseSignForWl";
    private static final String SQL_MAP_METHOD_REVERSE_BACK_BY_PAGE = "selectWmsReverseSignOfBackForWlByPage";
    private static final String SQL_MAP_METHOD_REVERSE_BACK_COUNT = "selectWmsReverseSignOfBackForWlCount";
    private static final String SQL_MAP_METHOD_REVERSE_EXCHANGE_BY_PAGE = "selectWmsReverseSignOfExchangeForWlByPage";
    private static final String SQL_MAP_METHOD_REVERSE_EXCHANGE_COUNT = "selectWmsReverseSignOfExchangeForWlCount";
    private static final String SQL_MAP_METHOD_REVERSE_REPAIR_BY_PAGE = "selectWmsReverseSignOfRepairForWlByPage";
    private static final String SQL_MAP_METHOD_REVERSE_REPAIR_COUNT = "selectWmsReverseSignOfRepairForWlCount";

    @Override
    public List<Map<String, Object>> selectWmsReverseSignOfBackByPage(Map<String, Object> params) {
        logger.debug("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackByPage(Map<String, Object> params) invoke");

        Long startTime = System.currentTimeMillis();

        List<Map<String, Object>> list ;
        try {
            list = findAllObjectsByPage2(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_REVERSE_BACK_BY_PAGE, params);
        }catch (Exception e){
            logger.error("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackByPage(Map<String, Object> params) failed, error msg:" + e.getMessage());
            list = new ArrayList<Map<String, Object>>();
        }

        logger.debug("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackByPage(Map<String, Object> params) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - startTime });
        return list;
    }

    @Override
    public Integer selectWmsReverseSignOfBackForWlCount(Map<String, Object> params) {
        logger.debug("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackForWlCount(Map<String, Object>) invoke");
        Long startTime = System.currentTimeMillis();

        Integer count;
        try {
            count = findAllObjectsCount(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_REVERSE_BACK_COUNT, params);
        }catch (Exception e){
            logger.error("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackForWlCount(Map<String, Object> params) failed, error msg:" + e.getMessage());
            count = 0;
        }

        logger.debug("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackForWlCount(Map<String, Object>) success: costTime={}ms",
                new Object[]{System.currentTimeMillis() - startTime});
        return count;
    }

    @Override
    public List<Map<String, Object>> selectWmsReverseSignOfExchangeForWlByPage(Map<String, Object> params) {
        logger.debug("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackByPage(Map<String, Object> params) invoke");

        Long startTime = System.currentTimeMillis();

        List<Map<String, Object>> list ;
        try {
            list = findAllObjectsByPage2(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_REVERSE_EXCHANGE_BY_PAGE, params);
        }catch (Exception e){
            logger.error("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackByPage(Map<String, Object> params) failed, error msg:" + e.getMessage());
            list = new ArrayList<Map<String, Object>>();
        }

        logger.debug("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackByPage(Map<String, Object> params) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - startTime });
        return list;
    }

    @Override
    public Integer selectWmsReverseSignOfExchangeForWlCount(Map<String, Object> params) {
        logger.debug("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackForWlCount(Map<String, Object>) invoke");
        Long startTime = System.currentTimeMillis();

        Integer count;
        try {
            count = findAllObjectsCount(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_REVERSE_EXCHANGE_COUNT, params);
        }catch (Exception e){
            logger.error("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackForWlCount(Map<String, Object> params) failed, error msg:" + e.getMessage());
            count = 0;
        }

        logger.debug("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackForWlCount(Map<String, Object>) success: costTime={}ms",
                new Object[]{System.currentTimeMillis() - startTime});
        return count;
    }

    @Override
    public List<Map<String, Object>> selectWmsReverseSignOfRepairForWlByPage(Map<String, Object> params) {
        logger.debug("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfRepairForWlByPage(Map<String, Object> params) invoke");

        Long startTime = System.currentTimeMillis();

        List<Map<String, Object>> list ;
        try {
            list = findAllObjectsByPage2(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_REVERSE_REPAIR_BY_PAGE, params);
        }catch (Exception e){
            logger.error("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfRepairForWlByPage(Map<String, Object> params) failed, error msg:" + e.getMessage());
            list = new ArrayList<Map<String, Object>>();
        }

        logger.debug("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfRepairForWlByPage(Map<String, Object> params) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - startTime });
        return list;
    }

    @Override
    public Integer selectWmsReverseSignOfRepairForWlCount(Map<String, Object> params) {
        logger.debug("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackForWlCount(Map<String, Object>) invoke");
        Long startTime = System.currentTimeMillis();

        Integer count;
        try {
            count = findAllObjectsCount(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_REVERSE_REPAIR_COUNT, params);
        }catch (Exception e){
            logger.error("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackForWlCount(Map<String, Object> params) failed, error msg:" + e.getMessage());
            count = 0;
        }

        logger.debug("IuniWmsReverseSignForWlDaoImpl.selectWmsReverseSignOfBackForWlCount(Map<String, Object>) success: costTime={}ms",
                new Object[]{System.currentTimeMillis() - startTime});
        return count;
    }
}
