package com.iuni.dp.service.datastat.service.common.impl;

import com.iuni.dp.persist.datastat.common.dao.OrderSourceDao;
import com.iuni.dp.persist.datastat.common.model.OrderSource;
import com.iuni.dp.service.datastat.service.common.OrderSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Service("orderSourceService")
public class OrderSourceServiceImpl implements OrderSourceService {

    @Autowired
    private OrderSourceDao orderSourceDao;

    @Override
    public List<OrderSource> getAllWmsOrderSource() {
        return orderSourceDao.selectAllWmsOrderSource();
    }

    @Override
    public List<OrderSource> getAllOmOrderSource() {
        return orderSourceDao.selectAllOmOrderSource();
    }

    @Override
    public List<OrderSource> getAllOmBigAccount() {
        return orderSourceDao.selectAllOmBigAccount();
    }

    @Override
    public List<OrderSource> getAllWmsTransferSource() {
        return orderSourceDao.selectAllWmsTransferSource();
    }
}
