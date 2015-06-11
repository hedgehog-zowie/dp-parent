package com.iuni.dp.service.datastat.service.wmswl;

import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniWmsStockSourceForWlService {

    /**
     * 仓库出入库来源汇总（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> querySumWmsStockSourceForWlByPage(Map<String, Object> params);

    /**
     * 仓库出入库来源汇总（总数）
     * @param params
     * @return
     */
    public Integer querySumWmsStockSourceForWlCount(Map<String, Object> params);

    /**
     * 仓库出入库来源明细（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryWmsStockSourceForWlByPage(Map<String, Object> params);

    /**
     * 仓库出入库来源明细（总数）
     * @param params
     * @return
     */
    public Integer queryWmsStockSourceForWlCount(Map<String, Object> params);

}
