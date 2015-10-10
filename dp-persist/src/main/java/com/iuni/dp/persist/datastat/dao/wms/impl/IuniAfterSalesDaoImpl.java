package com.iuni.dp.persist.datastat.dao.wms.impl;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.wms.IuniAfterSalesDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Repository("iuniAfterSalesDao")
public class IuniAfterSalesDaoImpl extends BaseDaoImpl<Object, Serializable> implements IuniAfterSalesDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<Map<String, Object>> selectIuniAfterSalesNumByPage(Map<String, Object> params) {
        logger.debug("IuniAfterSalesDaoImpl.selectIuniAfterSalesNumByPage(Map<String, Object>) invoke");
        Long stime = System.currentTimeMillis();

        List<Map<String, Object>> list = findAllObjectsByPage2("iuniAfterSales.selectAfterSalesNumOfOrderByPage", params);

        logger.debug("IuniAfterSalesDaoImpl.selectIuniAfterSalesNumByPage(Map<String, Object>) success: costTime={}ms",
                new Object[]{System.currentTimeMillis() - stime});
        return list;
    }

    @Override
    public Integer selectIuniAfterSalesNumCount(Map<String, Object> params) {
        logger.debug("IuniAfterSalesDaoImpl.selectIuniAfterSalesNumCount(Map<String, Object>) invoke");
        Long stime = System.currentTimeMillis();

        Integer count = (Integer) getObjectByProperty("iuniAfterSales.selectAfterSalesNumOfOrderCount", params);

        logger.debug("IuniAfterSalesDaoImpl.selectIuniAfterSalesNumCount(Map<String, Object>) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - stime });
        return count;
    }
}
