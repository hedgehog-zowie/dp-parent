package com.iuni.dp.persist.datastat.common.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.common.dao.IuniWmsSupplierDAO;
import com.iuni.dp.persist.datastat.common.model.IuniWmsSupplier;

@Repository("iuniWmsSupplierDAO")
public class IuniWmsSupplierDAOImpl extends BaseDaoImpl<IuniWmsSupplier, Serializable> implements IuniWmsSupplierDAO {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<IuniWmsSupplier> selectAllSuppliers() {
        logger.debug("IuniWmsSupplierDAOImpl.selectAllSuppliers() invoke");

        Long startTime = System.currentTimeMillis();

        List<IuniWmsSupplier> list = new ArrayList<IuniWmsSupplier>();
        try {
            list = findAllObjectsByPage(IuniWmsSupplier.class.getSimpleName() + ".selectAllSuppliers", null);
        } catch (Exception e) {
            logger.error("IuniWmsSupplierDAOImpl.selectAllSuppliers() failed, error mesg:", e.getMessage());
        }

        logger.debug("IuniWmsSupplierDAOImpl.selectAllSuppliers() success: costTime={}ms",
                new Object[]{System.currentTimeMillis() - startTime});
        return list;
    }


}