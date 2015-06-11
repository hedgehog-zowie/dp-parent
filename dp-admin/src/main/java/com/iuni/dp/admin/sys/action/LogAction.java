package com.iuni.dp.admin.sys.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.persist.sys.model.Log;
import com.iuni.dp.service.sys.constants.LogData;
import com.iuni.dp.service.sys.service.LogService;

/**
 * 日志的查询和删除
 * @author menglei
 */
@Controller("logAction")
@Scope("prototype")
public class LogAction extends BaseAction {

	private static final long serialVersionUID = -3588666311433814233L;

	@Autowired
	private LogService logService;

	private final static Logger LOGGER = LoggerFactory.getLogger(LogAction.class);
	
	private final static String BEGROW = "beginRow";
	private final static String ENDROW = "endRow"; 

	private String begDate;
	private String endDate;
	private String logType;
	private String operId;
	private String operName;
	private String supplierId;//供应商ID
	private String supplierName;//供应商名称
	private String logbustype;//业务日志类型
	private String logsystype;//系统日志类型
	
	private String[] ids = new String[]{};
 
	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	private String msg;
	
	private Log mopLog;

	private List<Log> logList;
	
	private String logId;
	 
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public String getBegDate() {
		return begDate;
	}

	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}
 
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public Log getMopLog() {
		return mopLog;
	}

	public void setMopLog(Log mopLog) {
		this.mopLog = mopLog;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Log> getLogList() {
		return logList;
	}

	public void setLogList(List<Log> logList) {
		this.logList = logList;
	}
	
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getLogbustype() {
		return logbustype;
	}

	public void setLogbustype(String logbustype) {
		this.logbustype = logbustype;
	}

	public String getLogsystype() {
		return logsystype;
	}

	public void setLogsystype(String logsystype) {
		this.logsystype = logsystype;
	}

	/**
	 * 查询日志(支持分页查询)
	 * @return String
	 */
	public String getLogs() {
		Map<String, Object> map = this.genParamMap();
		setTotalCounts(this.logService.getTotalCnt(map));
		
		int ttlPages = this.getTotalPages();
		pageIndex = (ttlPages != 0 && pageIndex > this.getTotalPages()) ? this.getTotalPages() :  pageIndex;
		
		setPageInfo2Map(map);
		List<Log> list = logService.getLog(map);
		for(Log log:list){
			if(null!=log.getLogType()&&!"".equals(log.getLogType())){
				log.setLogTypeName(LogData.logConstant.get(log.getLogType()));
			}
		}
		pageIndex = this.totalCounts == 0 ? 1 : pageIndex;
  
		this.setLogList(list);
		return SUCCESS;
	}
	
	/**
	 * 删除日志
	 * @return String
	 */
	public String deleteLogs() {
		//User usr = (User)request.getSession().getAttribute("user");
		try {
			if(this.ids != null && ids.length > 0){
				logService.deleteLog(this.ids);
			}else if(logId != null && !"".equals(logId)){
				logService.deleteLog(logId.split(","));
			}
			//logService.putLog2Memory(usr.getUser_id(), usr.getUser_name(), getIp(), "删除日志成功："+logId, LogType.);
			msg = "删除成功！";
		} catch (SQLException e) {
			msg = "删除失败！";
			//logService.putLog2Memory(usr.getUser_id(), usr.getUser_name(), getIp(), "删除日志失败："+logId, LogConstants.LOGTYPE_BIZLOG);
			e.printStackTrace();
			LOGGER.error("删除日志失败，日志ID："+logId,e);
		}
		getLogs();
		return SUCCESS;
	}
	
	/**
	 * 产生查询参数
	 * @return
	 */
	private Map<String, Object> genParamMap(){
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
 
		if(StringUtils.isNotEmpty((this.begDate))){
			paramMap.put("begDate", this.begDate);
		}
		if(StringUtils.isNotEmpty((this.endDate))){
			paramMap.put("endDate", this.endDate);
		}
		if(StringUtils.isNotEmpty((this.operId))){
			paramMap.put("operId", this.operId);
		}
		if(StringUtils.isNotEmpty((this.operName))){
			paramMap.put("operName", this.operName);
		}
	
		if(StringUtils.isNotEmpty((this.supplierId))){
			paramMap.put("supplierId", this.supplierId);
		}
		if(StringUtils.isNotEmpty((this.supplierName))){
			paramMap.put("supplierName", this.supplierName);
		}			
		if(StringUtils.isNotEmpty((this.logbustype))){
			paramMap.put("logType", this.logbustype);
		}else if(StringUtils.isNotEmpty((this.logsystype))){
			paramMap.put("logType", this.logsystype);
		}else{	
			if(StringUtils.isNotEmpty((this.logType))){
				paramMap.put("logType", this.logType);
			}
		}
		return paramMap;
	}
	
	private void setPageInfo2Map(Map<String, Object> paramMap){
		long start = (pageIndex-1)*pageSize;
	    long end = pageIndex*pageSize;
		paramMap.put(BEGROW, start);
		paramMap.put(ENDROW, end);
	}
	
}
