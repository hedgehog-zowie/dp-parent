package com.iuni.dp.service.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * DateUtil
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public class DateUtil
{

    public DateUtil()
    {
    }
    
    private static DateFormat getDateFormat(String formatStr) {
		if (formatStr.equalsIgnoreCase(DATE_FORMAT)) {
			return dateFormat;
		} else if (formatStr.equalsIgnoreCase(TIMEF_FORMAT)) {
			return dateTimeFormat;
		} else if (formatStr.equalsIgnoreCase(ZHCN_DATE_FORMAT)) {
			return zhcnDateFormat;
		} else if (formatStr.equalsIgnoreCase(ZHCN_TIME_FORMAT)) {
			return zhcnDateTimeFormat;
		} else if (formatStr.equalsIgnoreCase(DATE_STR_FORMAT)) {
			return zhcnDateStrFormat;
		} else {
			return new SimpleDateFormat(formatStr);
		}
	}

    public static String dateToDateString(Date date, String formatStr)
    {
        DateFormat df = getDateFormat(formatStr);
        if(date == null)
            return "";
        else
            return df.format(date);
    }

    public static String getCurrentStringDate(String strFormat)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        return format.format(cal.getTime());
    }

    public static Date getDateByStr(String dateStr)
    {
        if(dateStr == null || dateStr.equals(""))
            return null;
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
			d = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return d;
    }

    public static Date getDateByFormatStr(String dateStr, String format)
    {
        if(dateStr == null || dateStr.equals("") || format == null || format.equals(""))
            return null;
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
			d = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return d;
    }

	/**
	 * 获得当前月及当前月前11个月的数据
	 * 
	 * @return map
	 */
	public static List<Map<String,String>> getLast12Month() {
		LinkedHashMap<String, String> retMap ; 
		List<Map<String,String>> retList = new ArrayList<Map<String,String>>();
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		String DATE_FORMAT = "yyyyMM";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		String yearOfMonth = null;
		String year = null;
		String month = null;
		now.add(Calendar.MONTH, 1);
		for (int i = 0; i < 12; i++) {
			retMap = new LinkedHashMap<String, String>();
			now.add(Calendar.MONTH, -1);
			yearOfMonth = sdf.format(now.getTime());
			year = yearOfMonth.substring(0, 4);
			month = yearOfMonth.substring(4, 6);
			retMap.put("encodeKey", yearOfMonth);
			retMap.put("encodeValue", year + "年" + month + "月");
			retList.add(retMap);
		}
		return retList;
	}

	/**
	 * 获取当前季度和之前的三个季度
	 * 
	 * @return map
	 */
	public static List<Map<String,String>> getLast4Quarter() {
		LinkedHashMap<String, String> retMap;
		List<Map<String,String>> retList = new ArrayList<Map<String,String>>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if (month <= 3) {
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year + "1");
			retMap.put("encodeValue", year + "年第一季度");
			retList.add(retMap);
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year - 1 + "4");
			retMap.put("encodeValue", year - 1 + "年第四季度");
			retList.add(retMap);
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year - 1 + "3");
			retMap.put("encodeValue", year - 1 + "年第三季度");
			retList.add(retMap);
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year - 1 + "2");
			retMap.put("encodeValue", year - 1 + "年第二季度");
			retList.add(retMap);
		} else if (month > 3 && month <= 6) {
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year + "2");
			retMap.put("encodeValue", year + "年第二季度");
			retList.add(retMap);
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year + "1");
			retMap.put("encodeValue", year + "年第一季度");
			retList.add(retMap);
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year - 1 + "4");
			retMap.put("encodeValue", year - 1 + "年第四季度");
			retList.add(retMap);
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year - 1 + "3");
			retMap.put("encodeValue", year - 1 + "年第三季度");
			retList.add(retMap);
		} else if (month > 6 && month <= 9) {
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year + "3");
			retMap.put("encodeValue", year + "年第三季度");
			retList.add(retMap);
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year + "2");
			retMap.put("encodeValue", year + "年第二季度");
			retList.add(retMap);
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year + "1");
			retMap.put("encodeValue", year + "年第一季度");
			retList.add(retMap);
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year - 1 + "4");
			retMap.put("encodeValue", year - 1 + "年第四季度");
			retList.add(retMap);
		} else {
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year + "4");
			retMap.put("encodeValue", year + "年第四季度");
			retList.add(retMap);
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year + "3");
			retMap.put("encodeValue", year + "年第三季度");
			retList.add(retMap);
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year + "2");
			retMap.put("encodeValue", year + "年第二季度");
			retList.add(retMap);
			retMap = new LinkedHashMap<String, String>();
			retMap.put("encodeKey", year + "1");
			retMap.put("encodeValue", year + "年第一季度");
			retList.add(retMap);		
		}
		return retList;
	}
	

    public static Date getNextDay()
    {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        cal.add(5, 1);
        return cal.getTime();
    }
    
    public static Date getNextDay(Date date,int day){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(5, day);
        return cal.getTime();
    }
    
    public static Date getNextMinute(Date date, int minute) {
		return new Date(date.getTime() + minute * 60L * 1000L);
	}
    
    /**
     * @Description: 返回相对于date参数seconds多秒后的日期
     * @param Date date
     * @param int seconds
     * @return Date
     */
    public static Date getNextSeconds(Date date, int seconds) {
		return new Date(date.getTime() + seconds * 1000L);
	}
    
    /**
     * @Description:返回相对于date当天的凌晨日期 
     * @param Date date
     * @return Date
     */
    public static Date getMorningDateInTheDate(Date date) {
    	String sysTime = DateUtil.dateToDateString(date,DateUtil.TIMEF_FORMAT);
    	String morningTime = sysTime.substring(0, 11)+"00:00:00";
    	Date morningTimeDate = DateUtil.getDateByFormatStr(morningTime,DateUtil.TIMEF_FORMAT);
		return morningTimeDate;
	}

    public static Date getNextMonthFirstDay()
    {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        cal.add(2, 1);
        cal.set(5, 1);
        return cal.getTime();
    }

    // 默认显示日期的格式
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIMEF_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String TIMEF_FORMAT24 = "yyyy-MM-dd HH24:mi:ss";
	// 默认显示日期时间毫秒格式
	public static final String MSEL_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	// 默认显示简体中文日期的格式
	public static final String ZHCN_DATE_FORMAT = "yyyy年MM月dd日";
	// 默认显示简体中文日期时间的格式
	public static final String ZHCN_TIME_FORMAT = "yyyy年MM月dd日HH时mm分ss秒";
	// 默认显示简体中文日期时间毫秒格式
	public static final String ZHCN_MSEL_FORMAT = "yyyy年MM月dd日HH时mm分ss秒SSS毫秒";
	// 获取日期串格式
	public static final String DATE_STR_FORMAT = "yyyyMMdd";
	// 获取日期时间串格式
	public static final String TIME_STR_FORMAT = "yyyyMMddHHmmss";
	// 获取日期时间毫秒串格式
	public static final String MSEL_STR_FORMAT = "yyyyMMddHHmmssSSS";
    private static DateFormat dateFormat = null;
    private static DateFormat dateTimeFormat = null;
    private static DateFormat zhcnDateFormat = null;
    private static DateFormat zhcnDateTimeFormat = null;
    private static DateFormat zhcnDateStrFormat = null;

    static 
    {
    	zhcnDateStrFormat = new java.text.SimpleDateFormat(DATE_STR_FORMAT);
		dateFormat = new SimpleDateFormat(DATE_FORMAT);
		dateTimeFormat = new SimpleDateFormat(TIMEF_FORMAT);
		zhcnDateFormat = new SimpleDateFormat(ZHCN_DATE_FORMAT);
		zhcnDateTimeFormat = new SimpleDateFormat(ZHCN_TIME_FORMAT);
    }
    
    public static Date getValidTimeDate(){
    	
    	String dataString = dateToDateString(new Date(),DATE_FORMAT);
    	String timeString = "00:00:00";
    	return getDateByFormatStr(dataString+timeString,TIMEF_FORMAT24);
    }
    
    public static void main(String[] args){
    	//Calendar   cal   =   Calendar.getInstance(); 
    	//int   year   =   cal.get(Calendar.YEAR); 
    	//int   month   =   cal.get(Calendar.MONTH)   +   1; 
       	//int   day   =   cal.get(Calendar.DATE); 
    	//System.out.println("year="+year);
    	//System.out.println("month="+month);
    	//System.out.println("day="+day);
    	
    	//Date date =  getNextDay(new Date(),-365);
    	//String dateStr= dateToDateString(date,TIMEF_FORMAT);
    	//System.out.println("getNextDay(new Date(),-1)= "+date);
    	//System.out.println("getNextDay(new Date(),-1)= "+dateStr);
    	
    	Date date2 =  getNextMinute(new Date(),-60);
    	String dateStr2= dateToDateString(date2,TIMEF_FORMAT);
    	System.out.println("getNextMinute(new Date(),-60)= "+date2);
    	System.out.println("getNextMinute(new Date(),-60)= "+dateStr2);
    	
    	//String dateStr2= dateToDateString(new Date(),TIMEF_FORMAT);
    	//System.out.println(dateStr2);
    	//System.out.println(dateStr2.substring(11,16));
    	
    	//System.out.println(""+dateToDateString(new Date(),DATE_FORMAT));
    	//System.out.println(""+dateToDateString(new Date(),DATE_FORMAT)+" 00:00:00");
    	
    	//String dateTime = DateUtil.dateToDateString(new Date(),DateUtil.TIMEF_FORMAT);
    	//String newDateTime = dateTime.substring(0, 11)+"00:00:00";
    	//Date date = DateUtil.getDateByFormatStr(newDateTime,DateUtil.TIMEF_FORMAT);
    	
    	//System.out.println("dateTime="+dateTime);
    	//System.out.println("newDateTime="+newDateTime);
    	//System.out.println("date="+date);
    	
    	//Date datex = KeyNames.getBalanceUpdateTimeExpireTimeEnd();
    	//System.out.println(datex);
    }
}