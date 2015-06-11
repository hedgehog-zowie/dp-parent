package com.iuni.dp.service.datastat.service.wmswl;

import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniWmsStockChannelForWlService {

    /**
     * 各渠道进退换数量汇总报表（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryWmsStockChannelForWlByPage(Map<String, Object> params);

    /**
     * 各渠道进退换数量汇总报表（总数）
     * @param params
     * @return
     */
    public Integer queryWmsStockChannelForWlCount(Map<String, Object> params);

}
