package com.iuni.dp.persist.datastat.dao.wms.impl;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.wms.IuniRepairsDao;
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
@Repository("iuniRepairsDao")
public class IuniReparisDaoImpl extends BaseDaoImpl<Object, Serializable> implements IuniRepairsDao {

    private static final Logger logger = LoggerFactory.getLogger(IuniReparisDaoImpl.class);

    private static final String SQL_MAP_NAMESPACE = "IuniRepairs";
    private static final String SQL_MAP_METHOD_STOCK_CHANNEL_BY_PAGE = "selectIuniNotInWarrantyRepairsByPage";
    private static final String SQL_MAP_METHOD_STOCK_CHANNEL_COUNT = "selectIuniNotInWarrantyRepairsCount";

    @Override
    public List<Map<String, Object>> selectNotInWarrantyRepairsForWlByPage(Map<String, Object> params) {
        logger.debug("IuniReparisDaoImpl.selectNotInWarrantyRepairsForWlByPage(Map<String, Object> params) invoke");

        Long startTime = System.currentTimeMillis();

        List<Map<String, Object>> list;
        try {
            list = findAllObjectsByPage2(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_STOCK_CHANNEL_BY_PAGE, params);
        } catch (Exception e) {
            logger.error("IuniReparisDaoImpl.selectNotInWarrantyRepairsForWlByPage(Map<String, Object> params) failed, error msg:" + e.getMessage());
            list = new ArrayList<Map<String, Object>>();
        }

        logger.debug("IuniReparisDaoImpl.selectNotInWarrantyRepairsForWlByPage(Map<String, Object> params) success: costTime={}ms",
                new Object[]{System.currentTimeMillis() - startTime});
        return list;
    }

    @Override
    public Integer selectNotInWarrantyRepairsCount(Map<String, Object> params) {
        logger.debug("IuniReparisDaoImpl.selectNotInWarrantyRepairsCount(Map<String, Object>) invoke");
        Long startTime = System.currentTimeMillis();

        Integer count;
        try {
            count = findAllObjectsCount(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_STOCK_CHANNEL_COUNT, params);
        }catch (Exception e){
            logger.error("IuniReparisDaoImpl.selectNotInWarrantyRepairsCount(Map<String, Object> params) failed, error msg:" + e.getMessage());
            count = 0;
        }

        logger.debug("IuniReparisDaoImpl.selectNotInWarrantyRepairsCount(Map<String, Object>) success: costTime={}ms",
                new Object[]{System.currentTimeMillis() - startTime});
        return count;
    }

}
