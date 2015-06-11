package com.iuni.dp.service.datastat.service.wmswl;

import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniWmsStockForWlService {

    /**
     * 仓库出入库数量汇总（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> querySumWmsStockForWlByPage(Map<String, Object> params);

    /**
     * 仓库出入库数量汇总（总数）
     * @param params
     * @return
     */
    public Integer querySumWmsStockForWlCount(Map<String, Object> params);

    /**
     * 仓库出入库数量明细（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryWmsStockForWlByPage(Map<String, Object> params);

    /**
     * 仓库出入库数量明细（总数）
     * @param params
     * @return
     */
    public Integer queryWmsStockForWlCount(Map<String, Object> params);

}
