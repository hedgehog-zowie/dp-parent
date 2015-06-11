package com.iuni.dp.service.datastat.service.wmswl;

import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniWmsReverseSignForWlService {

    /**
     * 逆向签收表 - 退货（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryWmsReverseSignOfBackForWlByPage(Map<String, Object> params);

    /**
     * 逆向签收表 - 退货（总数）
     * @param params
     * @return
     */
    public Integer queryWmsReverseSignOfBackForWlCount(Map<String, Object> params);

    /**
     * 逆向签收表 - 换货（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryWmsReverseSignOfExchangeForWlByPage(Map<String, Object> params);

    /**
     * 逆向签收表 - 换货（总数）
     * @param params
     * @return
     */
    public Integer queryWmsReverseSignOfExchangeForWlCount(Map<String, Object> params);

    /**
     * 逆向签收表 - 维修（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryWmsReverseSignOfRepairForWlByPage(Map<String, Object> params);

    /**
     * 逆向签收表 - 维修（总数）
     * @param params
     * @return
     */
    public Integer queryWmsReverseSignOfRepairForWlCount(Map<String, Object> params);

}

