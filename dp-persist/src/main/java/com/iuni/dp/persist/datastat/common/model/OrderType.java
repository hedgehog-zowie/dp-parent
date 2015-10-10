package com.iuni.dp.persist.datastat.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public enum OrderType {

    PTDD("0", "普通订单"),
    SD("1", "刷单"),
    YJHX("2", "以旧换新"),;

    OrderType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private final String code;
    private final String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static List<OrderType> getAllOrderType(){
        List<OrderType> list = new ArrayList<OrderType>();
        list.add(PTDD);
        list.add(SD);
        list.add(YJHX);
        return list;
    }

}
