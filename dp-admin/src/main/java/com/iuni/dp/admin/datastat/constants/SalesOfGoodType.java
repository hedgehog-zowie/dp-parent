package com.iuni.dp.admin.datastat.constants;

import java.util.LinkedHashMap;
import java.util.Map;

public enum SalesOfGoodType {

	i1("i1手机"),
	U3("U3手机"),
	U2("U2手机"),
	PJ("配件");
	
	private String value;
	
	private SalesOfGoodType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	private static Map<String,String> goodsTypeList = new LinkedHashMap<String, String>();
	
	public static Map<String,String> getFGoodsTypeMap(){
		goodsTypeList.put(i1.toString(), SalesOfGoodType.i1.getValue());
		goodsTypeList.put(U3.toString(), SalesOfGoodType.U3.getValue());
		goodsTypeList.put(U2.toString(), SalesOfGoodType.U2.getValue());
		goodsTypeList.put(PJ.toString(), SalesOfGoodType.PJ.getValue());
		return goodsTypeList;
	}
}
