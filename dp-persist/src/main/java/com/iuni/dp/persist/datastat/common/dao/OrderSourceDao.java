package com.iuni.dp.persist.datastat.common.dao;

import com.iuni.dp.persist.datastat.common.model.OrderSource;

import java.util.List;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface OrderSourceDao {

    /**
     * 查询所有WMS的订单来源
     * @return
     */
    List<OrderSource> selectAllWmsOrderSource();

    /**
     * 查询所有订单系统的订单来源
     * @return
     */
    List<OrderSource> selectAllOmOrderSource();

    /**
     * 查询所有订单系统的大客户订单来源
     * @return
     */
    List<OrderSource> selectAllOmBigAccount();

    /**
     * 查询所有WMS的大客户订单来源
     * @return
     */
    List<OrderSource> selectAllWmsTransferSource();

}
