package com.iuni.dp.service.datastat.service.wmswl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.iuni.dp.service.common.exception.DBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.wms.IuniWmsTransferDao;
import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsReceiveDao;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsTransferStatForWlService;

@Service("iuniWmsTransferStatForWlService")
public class IuniWmsTransferStatForWlServiceImpl implements IuniWmsTransferStatForWlService {

    private static final Logger logger = LoggerFactory.getLogger(IuniWmsStockSourceForWlServiceImpl.class);

    @Autowired
    private IuniWmsTransferDao iuniWmsTransferDao;

    @Autowired
    private IuniWmsReceiveDao iuniWmsReceiveDao;

    @Override
    public Integer queryIuniWmsTransferStatForWlCount(Map<String, Object> params) {
        try {
            return iuniWmsTransferDao.selectIuniWmsTransferStatForWlCount(params);
        } catch (DataAccessException daex) {
            logger.error("IuniWmsTransferStatForWlServiceImpl.queryIuniWmsTransferStatForWlCount found DataAccessException", daex);
            throw new DBException(daex);
        }
    }

    @Override
    public List<Map<String, Object>> queryIuniWmsTransferStatForWlByPage(Map<String, Object> queryParams) {

        List<Map<String, Object>> list = null;
        try {
            list = iuniWmsTransferDao.selectIuniWmsTransferStatForWlByPage(queryParams);
        } catch (DataAccessException daex) {
            logger.error("IuniWmsTransferStatForWlServiceImpl.queryIuniWmsTransferStatForWlByPage found DataAccessException", daex);
            throw new DBException(daex);
        } catch (Exception e) {
            logger.error("IuniWmsTransferStatForWlServiceImpl.queryIuniWmsTransferStatForWlByPage found Exception", e);
            throw new DBException(e);
        }
        if (list == null)
            list = new ArrayList<Map<String, Object>>();

        return list;
    }

    @Override
    public List<Map<String, Object>> queryIuniWmsTransferForWL2Excel(Map<String, Object> queryParams) {
        // TODO Auto-generated method stub
        return iuniWmsTransferDao.selectIuniWmsTransferForWL2Excel(queryParams);
    }

    @Override
    public List<Map<String, Object>> queryIuniWmsReceiveForWLByPage(Map<String, Object> queryParams) {

        return iuniWmsReceiveDao.selectIuniWmsReceiveForWL(queryParams);
    }

    @Override
    public Integer queryIuniWmsReceiveForWLCount(
            Map<String, Object> queryParams2) {
        // TODO Auto-generated method stub
        return iuniWmsReceiveDao.selectIuniWmsReceiveForWlCount(queryParams2);
    }

    @Override
    public List<Map<String, Object>> queryIuniWmsReceiveForWL2Excel(
            Map<String, Object> queryParams2) {
        // TODO Auto-generated method stub
        return iuniWmsReceiveDao.selectIuniWmsReceiveForWl2Excel(queryParams2);
    }

}
