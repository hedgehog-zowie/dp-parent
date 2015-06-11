package com.iuni.dp.service.sys.constants;

import java.util.LinkedList;
import java.util.List;

import com.iuni.dp.persist.sys.model.Log;

public class LogList {
	
	public static  final Object LOGLISTLOCK = new Object();
	
	public static List<Log> list = new LinkedList<Log>();
	
}
