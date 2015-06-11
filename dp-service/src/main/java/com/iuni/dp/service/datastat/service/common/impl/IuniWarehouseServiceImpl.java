package com.iuni.dp.service.datastat.service.common.impl;

import com.iuni.dp.persist.datastat.common.dao.WarehouseDao;
import com.iuni.dp.persist.datastat.common.model.IuniWmsWarehouse;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.common.IuniWarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Service("iuniWarehouseService")
public class IuniWarehouseServiceImpl implements IuniWarehouseService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WarehouseDao warehouseDao;

    @Override
    public List<IuniWmsWarehouse> queryAllWarehouse() {
        List<IuniWmsWarehouse> list;

        try {
            list = warehouseDao.selectAllWarehouse();
            logger.debug("IuniWarehouseServiceImpl.queryAllWarehouse invoke success");
        } catch(DataAccessException daex) {
            logger.error("IuniWarehouseServiceImpl.queryAllWarehouse found DataAccessException", daex);
            throw new DBException(daex);
        } catch (Exception e2){
            logger.error("IuniWarehouseServiceImpl.queryAllWarehouse found Exception", e2);
            throw new DBException(e2);
        }
        if(list == null)
            list = new ArrayList<IuniWmsWarehouse>();

        return list;
    }

}
