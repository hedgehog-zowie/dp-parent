package com.iuni.dp.persist.datastat.common.dao.impl;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.common.dao.WarehouseDao;
import com.iuni.dp.persist.datastat.common.model.IuniWmsWarehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 仓库信息
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Repository("warehouseDao")
public class WarehouseDaoImpl extends BaseDaoImpl<IuniWmsWarehouse, Serializable> implements WarehouseDao {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseDaoImpl.class);

    @Override
    public List<IuniWmsWarehouse> selectAllWarehouse() {
        logger.debug("WarehouseDaoImpl.selectAllWarehouse() invoke");

        Long startTime = System.currentTimeMillis();

        List<IuniWmsWarehouse> list;
        try {
            list = findAllObjectsByPage("Warehouse.selectAllWarehouse", null);
        } catch(Exception e){
            logger.error("WarehouseDaoImpl.selectAllWarehouse() failed, error mesg:", e.getMessage());
            list = new ArrayList<IuniWmsWarehouse>();
        }

        logger.debug("WarehouseDaoImpl.selectAllWarehouse() success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - startTime });
        return list;
    }

}
