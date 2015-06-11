package com.iuni.dp.service.datastat.service.wmswl;

import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniWmsFreightForWlService {

    /**
     * 运费报表（分页查询）
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryWmsFreightForWlByPage(Map<String, Object> params);

    /**
     * 运费报表（总数）
     * @param params
     * @return
     */
    public Integer queryWmsFreightForWlCount(Map<String, Object> params);


}

