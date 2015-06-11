package com.iuni.dp.persist.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.iuni.dp.persist.common.constants.SysCons;

/**
 * 解析属性文件 dp-config.properties
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public class ParseProperties {
	
	private static ParseProperties parseProperties = new ParseProperties();
	private static Properties props;
	
	private ParseProperties() {
		init();
	}
	
	private void init() {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(SysCons.PROPERTIES_FILE_NAME);
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		props = new Properties();
		try {
			props.load(bf);
		} catch (Exception e) {
			System.err.println("Can not read the properties file; "+ "Make sure dp-config.properties is in the Classpath");
			return;
		}finally{
			try {
				bf.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// he report data file path.
	public final static String FILE_PATH = getPropertiesValue(SysCons.FILE_PATH);
	
	public static ParseProperties getInstance() {
		return parseProperties;
	}
	
	public static String getPropertiesValue(String key) {
		return props.getProperty(key);
	}
	
	public static Integer getIntVal(String key) {
		String val = getPropertiesValue(key);
		return Integer.valueOf(val);
	}
	
	public static Long getLongVal(String key) {
		String val = getPropertiesValue(key);
		return Long.valueOf(val);
	}
	
}
