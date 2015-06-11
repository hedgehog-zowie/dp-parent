package com.iuni.dp.service.datastat.service.common;

import com.iuni.dp.persist.datastat.common.model.OrderSource;

import java.util.List;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface OrderSourceService {

    /**
     * 查询所有WMS的订单来源
     * @return
     */
    public List<OrderSource> getAllWmsOrderSource();

    /**
     * 查询所有订单系统的订单来源
     * @return
     */
    public List<OrderSource> getAllOmOrderSource();

    /**
     * 查询所有订单系统的大客户订单来源
     * @return
     */
    public List<OrderSource> getAllOmBigAccount();

    /**
     * 查询所有WMS的大客户订单来源
     * @return
     */
    public List<OrderSource> getAllWmsTransferSource();

}
