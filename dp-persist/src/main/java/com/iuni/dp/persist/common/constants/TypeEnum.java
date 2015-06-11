package com.iuni.dp.persist.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志类型
 *
 * @author CaiKe
 *         dp-persist-1.0.0
 */
public class TypeEnum {

    public static final String SYSLOG = "1";// ("1","系统日志"),
    public static final String BUSLOG = "2";// ("2","业务日志"),
    public static Map<String, String> mapLog = new HashMap<String, String>();

    static {
        mapLog.put(SYSLOG, "系统日志");
        mapLog.put(BUSLOG, "业务日志");
    }

    /**
     * @author menglei
     * @dercript 系统日志类型枚举
     */
    public enum SysLogTypeEnum {
        // 11：自身系统异常, 12：外部系统对接异常
        SYSLOG("1", "系统日志"),
        INSYSLOG("11", "自身系统日志"),
        INSYSLOGSUCCESS("111", "系统正常日志"),
        INSYSLOGFAIL("112", "系统异常日志"),
        OUTSYSLOG("12", "外部系统对接异常");

        private String key;
        private String value;

        SysLogTypeEnum(String _key, String _value) {
            this.key = _key;
            this.value = _value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * @author menglei
     * @dercript 系统日志类型枚举
     */
    public enum BusLogTypeEnum {
        // 22：用户登陆
        BUSLOG("2", "业务日志"),
        USER_MANAGE("22", "用户信息日志");

        private String key;
        private String value;

        BusLogTypeEnum(String _key, String _value) {
            this.key = _key;
            this.value = _value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * @author menglei
     * @dercript 用户状态枚举
     */
    public enum UserStateEnum {

        USER_STATE_NORMAL("1", "正常"),
        USER_STATE_FREEZE("0", "冻结");

        private String key;
        private String value;

        UserStateEnum(String _key, String _value) {
            this.key = _key;
            this.value = _value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * @author menglei
     * @dercript 是否启用测试模式
     * Y：启用测试模式， 登录页面不显示验证码
     * N：不启用测试模式，登录页面显示验证码
     */
    public enum SecurityCodeStateEnum {

        CODE_STATE_TEST("Y", "测试模式"),
        CODE_STATE_NO_TEST("N", "非测试模式");

        private String key;
        private String value;

        SecurityCodeStateEnum(String _key, String _value) {
            this.key = _key;
            this.value = _value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * @author menglei
     * @dercript 文件上传状态枚举
     */
    public enum UploadStatus {

        Create_Directory_Fail(101, "远程服务器相应目录创建失败"),        // 远程服务器相应目录创建失败
        Create_Directory_Success(102, "远程服务器创建目录成功"),         // 远程服务器创建目录成功
        Upload_New_File_Success(103, "上传新文件成功"),                 // 上传新文件成功
        Upload_New_File_Failed(104, "上传新文件失败"),                  // 上传新文件失败
        File_Exits(105, "文件已经存在"),                                // 文件已经存在
        Remote_Bigger_Local(106, "远程文件大于本地文件"),                // 远程文件大于本地文件
        Upload_From_Break_Success(107, "断点续传成功"),                 // 断点续传成功
        Upload_From_Break_Failed(108, "断点续传失败"),                  // 断点续传失败
        Delete_Remote_Faild(109, "删除远程文件失败");                   // 删除远程文件失败

        private final Integer key;
        private final String value;

        private UploadStatus(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static Map<Integer, String> getList() {
            Map<Integer, String> map = new HashMap<Integer, String>();
            for (UploadStatus e : UploadStatus.values()) {
                map.put(e.getKey(), e.getValue());
            }
            return map;
        }

    }

    /**
     * @author menglei
     * @dercript 文件下载状态枚举
     */
    public enum DownloadStatus {

        Remote_File_Noexist(201, "远程文件不存在"),          // 远程文件不存在
        Download_New_File_Success(202, "下载文件成功"),      // 下载文件成功
        Download_New_File_Failed(203, "下载文件失败"),       // 下载文件失败
        Local_Bigger_Remote(204, "本地文件大于远程文件"),     // 本地文件大于远程文件
        Download_From_Break_Success(205, "断点续传成功"),     // 断点续传成功
        Download_From_Break_Failed(206, "断点续传失败");      // 断点续传失败

        private final Integer key;
        private final String value;

        private DownloadStatus(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static Map<Integer, String> getList() {
            Map<Integer, String> map = new HashMap<Integer, String>();
            for (DownloadStatus e : DownloadStatus.values()) {
                map.put(e.getKey(), e.getValue());
            }
            return map;
        }

    }

    /**
     * @author menglei
     * @dercript 用户鉴权错误码枚举
     */
    public enum BusiExcEnum {

        LOGIN_NAME_PASSWORD_NOT_EMPTY("101", "登录名和密码不能为空"),
        SECURITY_CODE_INVALID("102", "验证码无效"),
        SECURITY_CODE_ERROR("103", "验证码错误"),
        LOGIN_NAME_PASSWORD_ERROR("104", "登录名或者密码错误"),
        LOGIN_EXCEPTION("105", "登录异常,请联系管理员"),

        PARAMETER_EXCEPTION("201", "参数异常"),
        BUSINESS_EXCEPTION("202", "业务异常"),
        SYSTEM_EXCEPTION("203", "系统异常"),
        INTERRUPTED_EXCEPTION("204", "中断异常"),

        OPT_ID_NOT_EMPTY("301", "操作员ID不能为空"),
        LOGIN_NAME_NOT_EMPTY("302", "登录名不能为空"),
        LOGIN_NAME_ID_NOT_EMPTY("303", "登录名和操作员ID不能同时为空"),
        PASSWORD_NOT_EMPTY("304", "登录密码不能为空"),
        LOGIN_NAME_NOT_EXIST("305", "登录名不存在"),
        LOGIN_NAME_ID_NOT_EXIST("306", "登录名和操作员ID均不存在"),
        PASSWORD_ERROR("307", "登录密码错误"),
        USER_FREEZE("308", "用户被冻结"),

        SYS_ID_NOT_EMPTY("401", "系统ID不能为空"),
        SYS_NAME_NOT_EMPTY("402", "系统名不能为空"),
        SYS_ID_NAME_NOT_EMPTY("403", "系统ID和系统名称不能同时为空"),
        SYS_ID_NAME_NOT_EXIST("404", "系统ID和系统名称均不存在"),

        FILE_MKDIR_FAIL("501", "创建文件目录失败"),
        FILE_MOVE_FAIL("502", "移动文件失败"),
        FILE_RENAME_FAIL("503", "重命名文件失败"),
        FILE_BATCH_WRITE_LINES_FAIL("504", "批量行写文件失败"),
        FILE_BATCH_READ_LINES_FAIL("505", "批量行读文件失败"),
        FILE_WRITE_WAITE_TIME_FAIL("506", "文件批量写等待失败");

        private String key;
        private String value;

        BusiExcEnum(String _key, String _value) {
            this.key = _key;
            this.value = _value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * @author menglei
     * @dercript 发送策略code :11:立即发送  12：延迟发送 13:定时发送  14: 循环发送
     */
    public enum senderStrategyCodeEnum {

        IMMEDIATELY_SEND(11, "立即发送"),
        DELAY_SEND(12, "延迟发送"),
        TIMING_SEND(13, "定时发送"),
        CYCLE_SEND(14, "循环发送");

        private int key;
        private String value;

        senderStrategyCodeEnum(int _key, String _value) {
            this.key = _key;
            this.value = _value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * @author menglei
     * @dercript 执行策略Code 21:一次执行 22：多次执行
     */
    public enum executeStrategyCodeEnum {

        EXECUTE_AT_A_TIME(21, "一次执行"),
        EXECUTE_MANY_TIMES(22, "多次执行");

        private int key;
        private String value;

        executeStrategyCodeEnum(int _key, String _value) {
            this.key = _key;
            this.value = _value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * @author menglei
     * @dercript 失败策略Code 31:不处理 32：立即重试N次 33:延时重试N次
     */
    public enum failStrategyCodeEnum {

        DO_NOT_DEAL_WITH(31, "不处理"),
        IMMEDIATELY_RETRY_N_TIME(32, "立即重试N次"),
        DELAY_RETRY_N_TIME(33, "延时重试N次");

        private int key;
        private String value;

        failStrategyCodeEnum(int _key, String _value) {
            this.key = _key;
            this.value = _value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * @author menglei
     * @dercript 消息类型是否启用  1:启用 0:禁用 缺省值为1
     */
    public enum msgTypeFlagEnum {

        TO_ENABLE(1, "启用"),
        DISABLE(0, "禁用");

        private int key;
        private String value;

        msgTypeFlagEnum(int _key, String _value) {
            this.key = _key;
            this.value = _value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * @author menglei
     * @dercript 告警状态 1:进行中 2:处理完毕,缺省值为1
     */
    public enum alarmStateEnum {

        GOING_ON(1, "进行中"),
        PROCESS_COMPLETION(2, "处理完毕");

        private int key;
        private String value;

        alarmStateEnum(int _key, String _value) {
            this.key = _key;
            this.value = _value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * @author menglei
     * @dercript 是否发送标志位 1:发送 0:不发送,缺省值为0
     */
    public enum sendFlagEnum {

        SEND("1", "发送"),
        NO_SEND("0", "不发送");

        private String key;
        private String value;

        sendFlagEnum(String _key, String _value) {
            this.key = _key;
            this.value = _value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * @author menglei
     * @dercript 业务日志类型枚举
     */
    public enum BusLogEnum {

        SAVE_MSG_ALARM("21", "消息告警入库"), BATCH_SAVE_MSG_ALARM("22", "消息告警批量入库");

        private String key;
        private String value;

        BusLogEnum(String _key, String _value) {
            this.key = _key;
            this.value = _value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * @author menglei
     * @dercript 结果类型枚举
     */
    public enum ResultEnum {

        SUCESS(1, "操作成功"), FAIL(0, "操作失败");

        private Integer key;
        private String value;

        ResultEnum(Integer _key, String _value) {
            this.key = _key;
            this.value = _value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
