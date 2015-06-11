package com.iuni.dp.persist.datastat.dao.wmswl;

import com.iuni.dp.persist.common.dao.BaseDao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniWmsStockSourceForWlDao extends BaseDao<Object, Serializable> {

    /**
     * 仓库出入库数量汇总（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> selectSumWmsStockSourceForWlByPage(Map<String, Object> params);

    /**
     * 仓库出入库数量汇总（总数）
     * @param params
     * @return
     */
    public Integer selectSumWmsStockSourceForWlCount(Map<String, Object> params);

    /**
     * 仓库出入库数量明细（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> selectWmsStockSourceForWlByPage(Map<String, Object> params);

    /**
     * 仓库出入库数量明细（总数）
     * @param params
     * @return
     */
    public Integer selectWmsStockSourceForWlCount(Map<String, Object> params);

}
