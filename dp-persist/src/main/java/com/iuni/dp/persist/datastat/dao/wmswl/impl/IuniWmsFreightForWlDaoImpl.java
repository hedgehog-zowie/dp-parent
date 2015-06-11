package com.iuni.dp.persist.datastat.dao.wmswl.impl;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsFreightForWlDao;
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
@Repository("iuniWmsFreightForWlDao")
public class IuniWmsFreightForWlDaoImpl extends BaseDaoImpl<Object, Serializable> implements IuniWmsFreightForWlDao {

    private static final Logger logger = LoggerFactory.getLogger(IuniWmsFreightForWlDaoImpl.class);

    private static final String SQL_MAP_NAMESPACE = "IuniWmsFreight";
    private static final String SQL_MAP_METHOD_FREIGHT_PAGE = "selectWmsFreightByPage";
    private static final String SQL_MAP_METHOD_FREIGHT_COUNT = "selectWmsFreightCount";

    @Override
    public List<Map<String, Object>> selectWmsFreightByPage(Map<String, Object> params) {
        logger.debug("IuniWmsFreightForWlDaoImpl.selectWmsFreightByPage(Map<String, Object> params) invoke");

        Long startTime = System.currentTimeMillis();

        List<Map<String, Object>> list ;
        try {
            list = findAllObjectsByPage2(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_FREIGHT_PAGE, params);
        }catch (Exception e){
            logger.error("IuniWmsFreightForWlDaoImpl.selectWmsFreightByPage(Map<String, Object> params) failed, error msg:" + e.getMessage());
            list = new ArrayList<Map<String, Object>>();
        }

        logger.debug("IuniWmsFreightForWlDaoImpl.selectWmsFreightByPage(Map<String, Object> params) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - startTime });
        return list;
    }

    @Override
    public Integer selectWmsFreightCount(Map<String, Object> params) {
        logger.debug("IuniWmsFreightForWlDaoImpl.selectWmsFreightCount(Map<String, Object>) invoke");
        Long startTime = System.currentTimeMillis();

        Integer count;
        try {
            count = findAllObjectsCount(SQL_MAP_NAMESPACE + "." + SQL_MAP_METHOD_FREIGHT_COUNT, params);
        }catch (Exception e){
            logger.error("IuniWmsFreightForWlDaoImpl.selectWmsFreightCount(Map<String, Object> params) failed, error msg:" + e.getMessage());
            count = 0;
        }

        logger.debug("IuniWmsFreightForWlDaoImpl.selectWmsFreightCount(Map<String, Object>) success: costTime={}ms",
                new Object[]{System.currentTimeMillis() - startTime});
        return count;
    }

}
