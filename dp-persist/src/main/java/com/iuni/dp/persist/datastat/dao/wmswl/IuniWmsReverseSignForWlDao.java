package com.iuni.dp.persist.datastat.dao.wmswl;

import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniWmsReverseSignForWlDao {

    /**
     * 逆向签收表 - 退货（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> selectWmsReverseSignOfBackByPage(Map<String, Object> params);

    /**
     * 逆向签收表 - 退货（总数）
     * @param params
     * @return
     */
    public Integer selectWmsReverseSignOfBackForWlCount(Map<String, Object> params);

    /**
     * 逆向签收表 - 换货（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> selectWmsReverseSignOfExchangeForWlByPage(Map<String, Object> params);

    /**
     * 逆向签收表 - 换货（总数）
     * @param params
     * @return
     */
    public Integer selectWmsReverseSignOfExchangeForWlCount(Map<String, Object> params);

    /**
     * 逆向签收表 - 维修（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> selectWmsReverseSignOfRepairForWlByPage(Map<String, Object> params);

    /**
     * 逆向签收表 - 维修（总数）
     * @param params
     * @return
     */
    public Integer selectWmsReverseSignOfRepairForWlCount(Map<String, Object> params);
    
}
