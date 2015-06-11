package com.iuni.dp.admin.datastat.constants;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 订单来源
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public enum OrderRefer {
    IUNI("IUNI官网"),
    IUNIDXD("代客下单"),
    TAOBAO("天猫订单"),
    TAOBAO_PAID("淘宝-免支付订单"),
    IUNI_INSIDE("内部购机"),
    PAIPAI("拍拍"),
    PAIPAI_PAID("拍拍-免支付订单"),
    ;

    private String description;
    // 所有订单来源列表
    private static final Map<String, String> orderReferMap = new LinkedHashMap();
    // 仅官网
    private static final Map<String, String> orderReferMapForConv = new LinkedHashMap();

    OrderRefer(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 所有订单来源
     * @return
     */
    public static Map<String, String> getOrderReferMap() {
        if(orderReferMap.size() == 0) {
            orderReferMap.put(IUNI.toString(), IUNI.getDescription());
            orderReferMap.put(IUNIDXD.toString(), IUNIDXD.getDescription());
            orderReferMap.put(TAOBAO.toString(), TAOBAO.getDescription());
            orderReferMap.put(TAOBAO_PAID.toString(), TAOBAO_PAID.getDescription());
            orderReferMap.put(PAIPAI.toString(), PAIPAI.getDescription());
            orderReferMap.put(PAIPAI_PAID.toString(), PAIPAI_PAID.getDescription());
            orderReferMap.put(IUNI_INSIDE.toString(), IUNI_INSIDE.getDescription());
        }
        return orderReferMap;
    }

    /**
     * 仅IUNI官网
     * @return
     */
    public static Map<String, String> getOrderSourceMapForConv(){
        if(orderReferMapForConv.size() == 0) {
            orderReferMapForConv.put(IUNI.toString(), IUNI.getDescription());
            // orderReferMapForConv.put(IUNIDXD.toString(), IUNIDXD.getDescription());
        }
        return orderReferMapForConv;
    }

    /**
     * 商品销售报表 需要显示的订单来源
     */
    public static Map<String, String> getOrderReferMapForIuniSalesOfGoodsAction(){
        if(orderReferMap.size() == 0) {
            orderReferMap.put(IUNI.toString(), IUNI.getDescription());
            orderReferMap.put(IUNIDXD.toString(), IUNIDXD.getDescription());
            orderReferMap.put(TAOBAO.toString(), TAOBAO.getDescription());
            orderReferMap.put(PAIPAI.toString(), PAIPAI.getDescription());
            orderReferMap.put(TAOBAO_PAID.toString(), TAOBAO_PAID.getDescription());
            orderReferMap.put(PAIPAI_PAID.toString(), PAIPAI_PAID.getDescription());
        }
        return orderReferMap;
    }

}
