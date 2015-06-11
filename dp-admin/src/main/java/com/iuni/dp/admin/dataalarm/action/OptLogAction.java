package com.iuni.dp.admin.dataalarm.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.persist.dataalarm.model.OptLog;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.dataalarm.service.OptLogService;

@Controller("optLogAction")
@Scope("prototype")
public class OptLogAction extends BaseAction {

	private static final long serialVersionUID = -5881456518288024402L;
	private static final Logger logger = LoggerFactory.getLogger(OptLogAction.class);
	
	@Autowired
	private OptLogService optLogService;
	
	public String optLogListView() {

		try {
			List<OptLog> optLogs = optLogService.fetchAllOptLog();
			request.setAttribute("optLogs", optLogs);
			
		} catch(DBException dbex) {
			logger.error("OptLogAction.operaLogListView found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("OptLogAction.operaLogListView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
		
}
