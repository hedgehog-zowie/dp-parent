package com.iuni.dp.service.sys.constants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统常量在这里定义
 * @author menglei
 *
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
	 
	 // 测试模式，在dp-config.properties中属性ias.testmodel，如果值是Y，则是测试模式，登录时无验证码
	 public final static String TESTMODEL = "ias.testmodel";
	 
	 // 验证码在session中保存的关键字
	 public final static String RANDOM = "random";
	 
	 // 用户对象user在session中保存的关键字
	 public final static String USER = "user";
	 
	 // 用户Id，user_id在session中保存的关键字
	 public final static String USER_ID = "user_id";
	
	 /**
	  * 存放系统配置参数，系统启动后初始化
	  */
	 Map<String, String> SYS_PARAM_MAP = new ConcurrentHashMap<String, String>();
	 
	 int USER_STATUS_NORMAL = 1;// 正常用户状态
	 int USER_STATUS_FROZEN = 0;// 被冻结的用户状态
	 
	 // The batch safe msg alarm data max line limit(一次批量保存告警数据最大行数).
  	 public final static String BATCH_SAVE_MSG_ALARM_MAX_LINE_LIMIT = "batchSaveMsgAlarm.maxLineLimit";
  	 
     // The scan process alarm Schedule data max line limit at one time(一次扫描处理待排期告警最大记录条数).
  	 public final static String SCAN_PROCESS_ALARM_SCHEDULE_MAX_LIMIT_ONE_TIME = "scanProcessAlarmSchedule.maxLimitOneTime";
  	 
  	 // The scan process alarm Schedule data max interval time(扫描处理待排期告警记录的最大间隔时间,单位：秒).
  	 public final static String SCAN_PROCESS_ALARM_SCHEDULE_INTERVAL_TIME = "scanProcessAlarmSchedule.intervalTime";
  	 
     // mail.smtp.host
  	 public final static String MAIL_SERVER_HOST = "ias.mailServerHost";
  	 
     // mail.smtp.port
  	 public final static String MAIL_SERVER_PORT = "ias.mailServerPort";
  	 
  	 // mailFromAddress
  	 public final static String MAIL_FROM_ADDRESS = "ias.mailFromAddress";
  	 
     // mail.server.username
  	 public final static String MAIL_SERVER_USERNAME = "ias.mailServerUserName";
  	 
     // mail.server.password
  	 public final static String MAIL_SERVER_PASSWORD = "ias.mailServerPassword";
  	 
  	 // mail.server.validate
  	 public final static String MAIL_SERVER_VALIDATE = "ias.mailServerValidate";
  	 
     // mailSubject(邮件主题，专供测试使用)
  	 public final static String TEST_MAIL_SUBJECT = "ias.test.mailSubject";
  	 
     // mailContent(邮件内容，专供测试使用)
  	 public final static String TEST_MAIL_CONTENT = "ias.test.mailContent";
  	 
  	 // mailToAddress(收件人邮箱，专供测试使用)
  	 public final static String TEST_MAIL_TOADDRESS = "ias.test.mailToAddress";
  	  
     // 收件人邮件关键字
  	 public final static String EMAIL = "email";
  	 
     // 收件人短信关键字
  	 public final static String MOBILE = "mobile";
  	 
     // 操作员ID 系统操作：99999 其它操作员ID：当前操作员ID, 缺省值为99999
  	 public final static String SYS_OPT_ID = "99999";
  	 
  	 //微信图片服务器
  	public final static String WEIXIN_IMG_URL = "ias.weixin.img.url";
	 
}
