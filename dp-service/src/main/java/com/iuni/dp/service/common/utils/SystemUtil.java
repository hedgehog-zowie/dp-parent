package com.iuni.dp.service.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;

/**
 * 系统工具类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public class SystemUtil {

	/**
	 * 将byte数组转换成对象
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static Object unSerializeObject(byte[] bytes) throws Exception {
		if(bytes == null){
			return null;
		}
		Object object = null;
		ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
		ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(
		byteStream));
		object = is.readObject();
		is.close();
		return object;
	}
	
	/**
	 * 将对象转换成byte数组
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static byte[] serializeObject(Object object) throws Exception {
		if(object == null){
			return null;
		}
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream(5000);
		ObjectOutputStream os = new ObjectOutputStream(
		new BufferedOutputStream(byteStream));
		os.flush();
		os.writeObject(object);
		os.flush();
		byte[] returnValue = byteStream.toByteArray();
		os.close();
		os = null;
		return returnValue;
	}
	
	public static String format(String pattern, Double value) {
		if (pattern == null || value == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(value);
	}
	
	/**
	 * @Description: 返回 [min,max]范围内的随机数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getBetweenRandom(int min, int max) {
		return (int) Math.round(Math.random()*(max-min)+min);
	}
	
	/**
	 * @Description: 
	 * @param Object entityName 对象实例（比如：Report实例）
	 * @return List<String>
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> getNoEmptyFieldNameList(Object entityName)throws Exception {
		List<String> noEmptyFieldNameList = new ArrayList<String>();
		if (entityName != null) {
			Class c = entityName.getClass();
			Field field[] = c.getDeclaredFields();
			if (null != field) {
				for (Field f : field) {
					Object fieldValue = invokeMethod(entityName, f.getName(),null);
					if (fieldValue != null) {
						noEmptyFieldNameList.add(f.getName());
					}
				}
			}
		}
		return noEmptyFieldNameList;
	}

	/**
	 * @Description: 返回对象实例的某个具体属性的get方法返回的属性值
	 * @param Object entityName 对象实例（比如：Report实例）
	 * @param String fieldName  对象属性名称（比如：busCode）
	 * @param Object[] args 可以为null
	 * @return Object 返回当前对象（Report）实例，属性（busCode）的get方法返回的值
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object invokeMethod(Object entityName, String fieldName,Object[] args) throws Exception {
		Class entityClass = entityName.getClass();
		try {
			if (fieldName != null) {
				String methodName = getMethodName(fieldName);
				if (methodName != null) {
					Method method = entityClass.getMethod(methodName);
					if (method != null) {
						return method.invoke(entityName);
					}
				}
			}
		} catch (SecurityException e) {
			return null;
		} catch (NoSuchMethodException e) {
			return null;
		}
		return null;
	}
	
	/**
	 * @Description: 通过对象的属性名称，获取对象属性对应的get方法名称
	 * @param String fieldName 对象属性（比如：busCode）
	 * @return String 返回对象属性对应的get方法名称（比如：getBusCode）
	 * @throws Exception
	 */
	public static String getMethodName(String fieldName) throws Exception {
		try {
			if (fieldName != null) {
				fieldName = fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
				return "get" + fieldName;
			}
		} catch (SecurityException e) {
			return null;
		}
		return null;
	}
	
	/**
	 * @Description: 通过对象的属性名称，获取对象属性对应的get方法名称
	 * @param String fieldName 对象属性（比如：busCode）
	 * @return String 返回对象属性对应的get方法名称（比如：getBusCode）
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getLinkedMapByObject(Object obj) throws Exception {
		Map linkedMap = new LinkedMap();
		try {
			List<String> noEmptyFieldNameList = getNoEmptyFieldNameList(obj);
			Collections.sort(noEmptyFieldNameList);
			if (noEmptyFieldNameList != null && noEmptyFieldNameList.size() > 0) {
				for (String fieldName : noEmptyFieldNameList) {
					linkedMap.put(fieldName, invokeMethod(obj, fieldName, null));
				}
			}
		} catch (SecurityException e) {
			return null;
		}
		return linkedMap;
	}

}
