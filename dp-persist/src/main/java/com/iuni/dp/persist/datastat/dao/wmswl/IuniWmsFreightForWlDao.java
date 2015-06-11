package com.iuni.dp.persist.datastat.dao.wmswl;

import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniWmsFreightForWlDao {

    /**
     * 运费报表（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> selectWmsFreightByPage(Map<String, Object> params);

    /**
     * 运费报表（总数）
     * @param params
     * @return
     */
    public Integer selectWmsFreightCount(Map<String, Object> params);

}
