package com.iuni.dp.persist.datastat.common.dao;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.common.model.IuniWmsWarehouse;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface WarehouseDao extends BaseDao<IuniWmsWarehouse, Serializable> {

    public List<IuniWmsWarehouse> selectAllWarehouse();

}
