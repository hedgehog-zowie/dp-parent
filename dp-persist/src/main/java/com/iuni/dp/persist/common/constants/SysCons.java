package com.iuni.dp.persist.common.constants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统常量在这里定义
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public interface SysCons {
	
	 //基础属性配置文件
	 public final static String PROPERTIES_FILE_NAME = "dp-config.properties";
	
	 //系统启动后，日志保存延迟启动时间，以秒计
	 public final static String LOG_INIT_STARTDELAY = "log.inert.startDelay";
	 
	//日志保存间隔时间，以秒计
	 public final static String LOG_INIT_REPEATINTERVAL = "log.inert.repeatInterval";
	 
	 // 超级管理员账号
	 public final static String ADMINISTRATOR_USER = "administrator";
	 
	 // 测试模式，在dp-config.properties中属性drs.testmodel，如果值是Y，则是测试模式，登录时无验证码
	 public final static String TESTMODEL = "drs.testmodel";
	 
	 // 验证码在session中保存的关键字
	 public final static String RANDOM = "random";
	 
	 // 用户对象user在session中保存的关键字
	 public final static String USER = "user";
	 
	 // 用户Id，user_id在session中保存的关键字
	 public final static String USER_ID = "user_id";
	 
	 //The report data queue's number.
	 public final static String QUEUE_NUMBER = "queue.number";
	 
	 // The report data queue's capacity.
	 public final static String QUEUE_CAPACITY = "queue.capacity";
	 
	 // he report data file path.
	 public final static String FILE_PATH = "file.path";
	 
	 // The report data back file path.
	 public final static String BACK_FILE_PATH = "backFile.path";
	 
	 // The rule engine rule file drl path（规则引擎用到的规则文件的目录，目录下方的是扩展名为drl的规则文件）.
	 public final static String RULE_ENGINE_RULE_CONFIG_PATH = "ruleEngine.ruleConfigPath";
	 
	 // The report data file max line limit.
	 public final static String FILE_MAX_LINE_LIMIT = "file.maxLineLimit";
	 
	 // The report data file write wait max Time(单位：秒).
     public final static String FILE_WRITE_WAITE_TIME = "file.writeWaitTime";
     
     // The tack queue max interval time(出队列最大间隔时间,单位：秒).
     public final static String TACK_QUEUE_INTERVAL_TIME = "tack.queue.intervalTime";
     
     // The parse safeDb max interval time(解析文件入库的最大间隔时间,单位：秒).
     public final static String PARSE_SAFEDB_INTERVAL_TIME = "parse.safeDb.intervalTime";
     
	 // 消息服务器地址
     public final static String ACTIVE_MQ_TCP = "activeMQ.tcp";
     
     // 消息服务器用户名（配置为空，表示用消息服务器缺省的用户名）
     public final static String ACTIVE_MQ_USER = "activeMQ.user";
     
     // 消息服务器密码（配置为空，表示用消息服务器缺省的密码）
     public final static String ACTIVE_MQ_PASSWORD = "activeMQ.password";
     
     // The batch report data max line limit(一次批量上报数据最大行数).
  	 public final static String BATCH_DATA_REPORT_MAX_LINE_LIMIT = "batchDataReport.maxLineLimit";
	
	 //存放系统配置参数，系统启动后初始化
	 public final static Map<String, String> SYS_PARAM_MAP = new ConcurrentHashMap<String, String>();
	 
	 //正常用户状态
	 public final static int USER_STATUS_NORMAL = 1;
	 
	 //被冻结的用户状态
	 public final static int USER_STATUS_FROZEN = 0;
	 
}
