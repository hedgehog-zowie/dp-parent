package com.iuni.dp.persist.datastat.dao.wms.impl;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.wms.IuniWeixinPayDao;
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
@Repository("iuniWeixinPayDao")
public class IuniWeixinPayDaoImpl extends BaseDaoImpl<Object, Serializable> implements IuniWeixinPayDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<Map<String, Object>> selectIuniWeixinPayByPage(Map<String, Object> params) {
        logger.debug("IuniWeixinPayDaoImpl.selectIuniWeixinPayByPage(Map<String, Object>) invoke");
        Long stime = System.currentTimeMillis();

        List<Map<String, Object>> list = findAllObjectsByPage2("iuniWeixinPay.selectIuniWeixinPayByPage", params);

        logger.debug("IuniWeixinPayDaoImpl.selectIuniWeixinPayByPage(Map<String, Object>) success: costTime={}ms",
                new Object[]{System.currentTimeMillis() - stime});
        return list;
    }

    @Override
    public Integer selectIuniWeixinPayCount(Map<String, Object> params) {
        logger.debug("IuniWeixinPayDaoImpl.selectIuniWeixinPayCount(Map<String, Object>) invoke");
        Long stime = System.currentTimeMillis();

        Integer count = (Integer) getObjectByProperty("iuniWeixinPay.selectIuniWeixinPayCount", params);

        logger.debug("IuniWeixinPayDaoImpl.selectIuniWeixinPayCount(Map<String, Object>) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - stime });
        return count;
    }
}
