package com.iuni.dp.persist.datastat.common.dao.impl;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.common.dao.OrderSourceDao;
import com.iuni.dp.persist.datastat.common.model.IuniWmsSupplier;
import com.iuni.dp.persist.datastat.common.model.OrderSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Repository("orderSourceDao")
public class OrderSourceDaoImpl extends BaseDaoImpl<OrderSource, Serializable> implements OrderSourceDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<OrderSource> selectAllWmsOrderSource() {
        logger.debug("OrderSourceDaoImpl.selectAllWmsOrderSource() invoke");

        Long startTime = System.currentTimeMillis();

        List<OrderSource> list = new ArrayList<OrderSource>();
        try {
            list = findAllObjectsByPage("OrderSource.selectWmsOrderSource", null);
        } catch (Exception e) {
            logger.error("OrderSourceDaoImpl.selectAllWmsOrderSource() failed, error mesg:", e.getMessage());
        }

        logger.debug("OrderSourceDaoImpl.selectAllWmsOrderSource() success: costTime={}ms", new Object[]{System.currentTimeMillis() - startTime});

        return list;
    }

    @Override
    public List<OrderSource> selectAllOmOrderSource() {
        logger.debug("OrderSourceDaoImpl.selectAllOmOrderSource() invoke");

        Long startTime = System.currentTimeMillis();

        List<OrderSource> list = new ArrayList<OrderSource>();
        try {
            list = findAllObjectsByPage("OrderSource.selectOMOrderSource", null);
        } catch (Exception e) {
            logger.error("OrderSourceDaoImpl.selectAllOmOrderSource() failed, error mesg:", e.getMessage());
        }

        logger.debug("OrderSourceDaoImpl.selectAllOmOrderSource() success: costTime={}ms", new Object[]{System.currentTimeMillis() - startTime});

        return list;
    }

    @Override
    public List<OrderSource> selectAllOmBigAccount() {
        logger.debug("OrderSourceDaoImpl.selectAllOmBigAccount() invoke");

        Long startTime = System.currentTimeMillis();

        List<OrderSource> list = new ArrayList<OrderSource>();
        try {
            list = findAllObjectsByPage("OrderSource.selectOMBigAccount", null);
        } catch (Exception e) {
            logger.error("OrderSourceDaoImpl.selectAllOmBigAccount() failed, error mesg:", e.getMessage());
        }

        logger.debug("OrderSourceDaoImpl.selectAllOmBigAccount() success: costTime={}ms", new Object[]{System.currentTimeMillis() - startTime});

        return list;
    }

    @Override
    public List<OrderSource> selectAllWmsTransferSource() {
        logger.debug("OrderSourceDaoImpl.selectAllWmsTransferSource() invoke");

        Long startTime = System.currentTimeMillis();

        List<OrderSource> list = new ArrayList<OrderSource>();
        try {
            list = findAllObjectsByPage("OrderSource.selectWmsTransferSource", null);
        } catch (Exception e) {
            logger.error("OrderSourceDaoImpl.selectAllWmsTransferSource() failed, error mesg:", e.getMessage());
        }

        logger.debug("OrderSourceDaoImpl.selectAllWmsTransferSource() success: costTime={}ms", new Object[]{System.currentTimeMillis() - startTime});

        return list;
    }
}
