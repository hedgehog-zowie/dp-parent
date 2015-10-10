package com.iuni.dp.persist.datastat.dao.wms.impl;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.wms.IuniAlipayDao;
import com.iuni.dp.persist.datastat.dao.wms.IuniWmsTransferDao;
import com.iuni.dp.persist.datastat.model.IuniWmsTransfer;
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
@Repository("iuniAlipayDao")
public class IuniAlipayDaoImpl extends BaseDaoImpl<Object, Serializable> implements IuniAlipayDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<Map<String, Object>> selectIuniAlipayByPage(Map<String, Object> params) {
        logger.debug("IuniAlipayDaoImpl.selectIuniAlipayByPage(Map<String, Object>) invoke");
        Long stime = System.currentTimeMillis();

        List<Map<String, Object>> list = findAllObjectsByPage2("iuniAlipay.selectIuniAlipayByPage", params);

        logger.debug("IuniAlipayDaoImpl.selectIuniAlipayByPage(Map<String, Object>) success: costTime={}ms",
                new Object[]{System.currentTimeMillis() - stime});
        return list;
    }

    @Override
    public Integer selectIuniAlipayCount(Map<String, Object> params) {
        logger.debug("IuniAlipayDaoImpl.selectIuniAlipayCount(Map<String, Object>) invoke");
        Long stime = System.currentTimeMillis();

        Integer count = (Integer) getObjectByProperty("iuniAlipay.selectIuniAlipayCount", params);

        logger.debug("IuniAlipayDaoImpl.selectIuniAlipayCount(Map<String, Object>) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - stime });
        return count;
    }
}
