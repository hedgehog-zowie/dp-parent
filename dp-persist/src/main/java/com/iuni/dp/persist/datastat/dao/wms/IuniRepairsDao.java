package com.iuni.dp.persist.datastat.dao.wms;

import com.iuni.dp.persist.common.dao.BaseDao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniRepairsDao extends BaseDao<Object, Serializable> {
    /**
     * 非保修维修单报表（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> selectNotInWarrantyRepairsForWlByPage(Map<String, Object> params);

    /**
     * 非保修维修单报表（总数）
     * @param params
     * @return
     */
    public Integer selectNotInWarrantyRepairsCount(Map<String, Object> params);
}
