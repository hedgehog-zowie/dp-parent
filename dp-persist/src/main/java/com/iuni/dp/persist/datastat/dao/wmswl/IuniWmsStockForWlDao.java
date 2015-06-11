package com.iuni.dp.persist.datastat.dao.wmswl;

import com.iuni.dp.persist.common.dao.BaseDao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniWmsStockForWlDao extends BaseDao<Object, Serializable> {

    /**
     * 仓库出入库数量汇总（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> selectSumWmsStockForWlByPage(Map<String, Object> params);

    /**
     * 仓库出入库数量汇总（总数）
     * @param params
     * @return
     */
    public Integer selectSumWmsStockForWlCount(Map<String, Object> params);

    /**
     * 仓库出入库数量明细（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> selectWmsStockForWlByPage(Map<String, Object> params);

    /**
     * 仓库出入库数量明细（总数）
     * @param params
     * @return
     */
    public Integer selectWmsStockForWlCount(Map<String, Object> params);

}
