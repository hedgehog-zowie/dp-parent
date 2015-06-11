package com.iuni.dp.service.datastat.service.common;

import com.iuni.dp.persist.datastat.common.model.IuniWmsWarehouse;

import java.util.List;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniWarehouseService {

    /**
     * 查询所有仓库信息
     * @return
     */
    public List<IuniWmsWarehouse> queryAllWarehouse();

}
