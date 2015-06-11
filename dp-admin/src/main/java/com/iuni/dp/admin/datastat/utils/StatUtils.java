package com.iuni.dp.admin.datastat.utils;

public class StatUtils {

	public static String convertStatType4PfResponse(Integer statType) {
		
		String result = null;
		
		if(statType == 100) {
			result = "小于0秒";
		} else if(statType == 101) {
			result = "0~1秒";
		} else if(statType == 102) {
			result = "1~2秒";
		} else if(statType == 103) {
			result = "2~3秒";
		} else if(statType == 104) {
			result = "3~4秒";
		} else if(statType == 105) {
			result = "4~5秒";
		} else if(statType == 106) {
			result = "5~10秒";
		} else if(statType == 107) {
			result = "10~20秒";
		} else if(statType == 108) {
			result = "20~30秒";
		} else if(statType == 109) {
			result = "30~60秒";
		} else if(statType == 110) {
			result = "大于60秒";
		}
		
		return result;
	}
	
}
