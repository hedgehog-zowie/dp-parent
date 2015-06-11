package com.iuni.dp.service.datastat.service.wms;

import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniRepairsService {

    /**
     * 非保修维修单报表（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryNotInWarrantyRepairsForWlByPage(Map<String, Object> params);

    /**
     * 非保修维修单报表（总数）
     * @param params
     * @return
     */
    public Integer queryNotInWarrantyRepairsCount(Map<String, Object> params);

}
