package com.iuni.dp.admin.datastat.constants;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public enum DateStyle {

    YYYYMMDD("YYYY-MM-DD"),
    YYYYMM("YYYY-MM"),
    YYYYIW("YYYY-IW");
//    YYYY("YYYY");

    private String dateStyleStr;
    private static final Map<String, String> dateStyleMap = new LinkedHashMap<String, String>();
    private static final Map<String, String> dateStyleMap2 = new LinkedHashMap<String, String>();

    DateStyle(String dateStyleStr) {
        this.dateStyleStr = dateStyleStr;
    }

    public String getDateStyleStr() {
        return this.dateStyleStr;
    }

    public static Map<String, String> getDateStyleMap(){
        if(dateStyleMap.size() == 0) {
            dateStyleMap.put(YYYYMMDD.getDateStyleStr(), "按天");
            dateStyleMap.put(YYYYMM.getDateStyleStr(), "按月");
//            dateStyleMap.put(YYYY.getDateStyleStr(), "按年");
        }
        return dateStyleMap;
    }
    
    public static Map<String, String> getDatestyleMap2(){
        if(dateStyleMap2.size() == 0) {
        	dateStyleMap2.put(YYYYMMDD.getDateStyleStr(), "按天");
        	dateStyleMap2.put(YYYYIW.getDateStyleStr(), "按周");
        	dateStyleMap2.put(YYYYMM.getDateStyleStr(), "按月");
        }
        return dateStyleMap2;
    }

}
