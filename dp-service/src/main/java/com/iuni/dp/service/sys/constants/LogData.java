package com.iuni.dp.service.sys.constants;

import java.util.HashMap;
import java.util.Map;

public class LogData {
	
	public static Map<String,String> logConstant=new HashMap<String,String>();
	
	static {
		
		logConstant.put("1","系统日志");
		logConstant.put("11","内部系统日志");
		logConstant.put("111","系统正常日志");
		logConstant.put("112","系统异常日志");
		
		logConstant.put("2","业务日志");
		logConstant.put("21","商品日志");
		logConstant.put("211","商品新增");
		logConstant.put("212","商品修改");
		logConstant.put("213","商品删除");
		logConstant.put("214","导入库存");
		logConstant.put("215","审核商品");
		logConstant.put("216","商品同步");
		logConstant.put("22","用户信息日志");
		logConstant.put("23","商户日志");
		logConstant.put("24","订单日志");
		
		logConstant.put("25","排期管理");
		logConstant.put("251","排期新增");
		logConstant.put("252","排期修改");
		logConstant.put("253","排期删除");
		logConstant.put("254","排期确认");
		logConstant.put("255","排期接收");
		logConstant.put("256","排期不接收");
		
	}
	
}
