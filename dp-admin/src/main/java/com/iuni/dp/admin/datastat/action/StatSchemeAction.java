package com.iuni.dp.admin.datastat.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.admin.common.utils.ActionUtils;
import com.iuni.dp.admin.datastat.constants.StatSchemeTaskType;
import com.iuni.dp.admin.datastat.task.StatSchemeProcessTask;
import com.iuni.dp.admin.datastat.vo.StatSchemeVo;
import com.iuni.dp.persist.datastat.model.StatScheme;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.StatSchemeService;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * 数据统计计划Action
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
@Controller("statSchemeAction")
@Scope("prototype")
public class StatSchemeAction extends BaseAction {

	private static final long serialVersionUID = -287595144374047832L;

	private static final Logger logger = LoggerFactory.getLogger(StatSchemeAction.class);
	
	@Autowired
	private StatSchemeService statSchemeService;
	
	@Autowired
	private ThreadPoolTaskExecutor executor;
	
	@Autowired
	private Scheduler qtzJobScheduler;
	
	private StatScheme statScheme;
	private StatSchemeVo statSchemeVo;
	private String flag;
	
	public StatScheme getStatScheme() {
		return statScheme;
	}

	public void setStatScheme(StatScheme statScheme) {
		this.statScheme = statScheme;
	}

	public StatSchemeVo getStatSchemeVo() {
		return statSchemeVo;
	}

	public void setStatSchemeVo(StatSchemeVo statSchemeVo) {
		this.statSchemeVo = statSchemeVo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 统计分析计划列表视图
	 * @return String
	 */
	public String statSchemeListView() {
		long stime = System.currentTimeMillis();
		List<StatScheme> statSchemes = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap();
			//接收人信息列表总数目分页查询
			Integer totalRecord = statSchemeService.getTotalCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(params);
			
			statSchemes = statSchemeService.getStatSchemeByPage(params);
			
			request.setAttribute("statSchemes", statSchemes);
			this.setPage(page);
			
			logger.debug("StatSchemeAction.statSchemeListView invoke success, costTime={}ms"
					, new Object[]{System.currentTimeMillis() - stime});
			
		} catch(DBException dbex) {
			logger.error("StatSchemeAction.statSchemeListView found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("StatSchemeAction.statSchemeListView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 按JSON格式获取统计分析计划列表
	 */
	public void getStatSchemeListByJSON() {
		long stime = System.currentTimeMillis();
		List<StatScheme> statSchemes = null;		

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if(null != statSchemeVo) {
				Integer status = statSchemeVo.getStatus();
				if(null != status) {
					params.put("status", status);
				}
			}
			statSchemes = statSchemeService.getAllStatScheme(params);
			
			logger.debug("StatSchemeAction.getStatSchemeListByJSON invoke success, costTime={}ms"
					, new Object[]{System.currentTimeMillis() - stime});
			
		} catch(DBException dbex) {
			logger.error("StatSchemeAction.getStatSchemeListByJSON found DBException", dbex);
			
		} catch(Exception ex) {
			logger.error("StatSchemeAction.getStatSchemeListByJSON found Exception", ex);
		}
	
		printJson2(statSchemes, Boolean.TRUE, Boolean.TRUE);
	}
	
	/**
	 * 按JSON格式获取统计分析计划
	 */
	public void getStatSchemeByJSON() {
		long stime = System.currentTimeMillis();
		StatScheme stScheme = null;
		
		try {
			if(null != statScheme) {
				Integer id = statScheme.getId();
				if(null != id) {
					stScheme = statSchemeService.getStatSchemeById(id);
				}
			}
			
			logger.debug("StatSchemeAction.getStatSchemeByJSON invoke success, costTime={}ms"
					, new Object[]{System.currentTimeMillis() - stime});
			
		} catch(DBException dbex) {
			logger.error("StatSchemeAction.getStatSchemeListByJSON found DBException", dbex);
			
		} catch(Exception ex) {
			logger.error("StatSchemeAction.getStatSchemeListByJSON found Exception", ex);
		}
	
		printJson2(stScheme, Boolean.TRUE, Boolean.TRUE);
	}
	
	/**
	 * 生成查询参数Map
	 * @return Map<String, Object>
	 */
	private Map<String, Object> genParamMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(null != statSchemeVo) {
			if(StringUtils.isNotBlank(statSchemeVo.getCode())) {
				params.put("code", statSchemeVo.getCode());
			}
			if(StringUtils.isNotBlank(statSchemeVo.getName())) {
				params.put("name", statSchemeVo.getName());
			}
			if(null != statSchemeVo.getStatus()) {
				params.put("status", statSchemeVo.getStatus());
			}
			if(null != statSchemeVo.getStatStrategyCode()) {
				params.put("statStrategyCode", statSchemeVo.getStatStrategyCode());
			}
			if(StringUtils.isNotBlank(statSchemeVo.getRptDataType())) {
				params.put("rptDataType", statSchemeVo.getRptDataType());
			}
			if(StringUtils.isNotBlank(statSchemeVo.getStatField())) {
				params.put("statField", statSchemeVo.getStatField());
			}
			if(StringUtils.isNotBlank(statSchemeVo.getBeginDate())) {
				params.put("beginDate", statSchemeVo.getBeginDate());
			}
			if(StringUtils.isNotBlank(statSchemeVo.getEndDate())) {
				params.put("endDate", statSchemeVo.getEndDate());
			}
			
		}
		
		return params;
	}
	
	/**
	 * 新增Page至参数Map
	 * @param Map<String, Object> params
	 */
	private void setPageInfo2Map(Map<String, Object> params) {
		params.put("startRec", page.getStartRec());
		params.put("endRec", page.getEndRec());
	}
	
	/**
	 * 统计分析计划编辑视图
	 * @return String
	 */
	public String statSchemeEditView() {
		long stime = System.currentTimeMillis();
		
		if(StringUtils.isNotBlank(flag) && "update".equals(flag)) {
			try {
				Integer id = statScheme.getId();
				StatScheme statScheme = statSchemeService.getStatSchemeById(id);
				
				this.setFlag(flag);
				this.setStatScheme(statScheme);
				
				logger.debug("StatSchemeAction.statSchemeEditView invoke success, costTime={}ms"
						, new Object[]{System.currentTimeMillis() - stime});
				
			} catch(DBException dbex) {
				logger.error("StatSchemeAction.statSchemeEditView found DBException", dbex);
				return ERROR;
				
			} catch(Exception ex) {
				logger.error("StatSchemeAction.statSchemeEditView found Exception", ex);
				return ERROR;
			}
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 新增统计分析计划
	 * @return String
	 */
	@Validations(requiredStrings = { @RequiredStringValidator(type = ValidatorType.SIMPLE, 
			fieldName = "statScheme.code", shortCircuit = true, key = "statScheme.code.empty"), 
			@RequiredStringValidator(type = ValidatorType.SIMPLE, 
			fieldName = "statScheme.name", shortCircuit = true, key = "statScheme.name.empty"),
			@RequiredStringValidator(type = ValidatorType.SIMPLE, 
			fieldName = "statScheme.rptDataType", shortCircuit = true, key = "statScheme.rptDataType.empty"),
			@RequiredStringValidator(type = ValidatorType.SIMPLE, 
			fieldName = "statScheme.statField", shortCircuit = true, key = "statScheme.statField.empty")})
	public String addStatScheme() {
		long stime = System.currentTimeMillis();
		
		try {
			statScheme.setCreatorId(ActionUtils.getCurrentUser().getId().intValue());
			
			Integer statSchemeId = statSchemeService.saveStatScheme(statScheme);
			
			// 统计分析计划配置任务处理
			executor.execute(new StatSchemeProcessTask(qtzJobScheduler, statSchemeService, statSchemeId, StatSchemeTaskType.AddOrUpdate));
			
			logger.debug("StatSchemeAction.addStatScheme invoke success, costTime={}ms"
					, new Object[]{System.currentTimeMillis() - stime});
			
		} catch(DBException dbex) {
			logger.error("StatSchemeAction found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("StatSchemeAction found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 新增统计分析计划业务校验
	 */
	public void validateAddStatScheme() {
		if(null != statScheme) {
			if(statScheme.getStatStrategyCode() == 11) {
				if(null == statScheme.getStatScheduledTime()) {
					this.addFieldError("statScheme.statScheduledTime", this.getText("statScheme.scheduledTime.empty"));
				}
				if(null != statScheme.getStatSnapshotTime()) {
					this.addFieldError("statScheme.statSnapshotTime", this.getText("statScheme.snapshotTime.unabled"));
				}
			} else if(statScheme.getStatStrategyCode() == 12) {
				if(null == statScheme.getStatSnapshotTime()) {
					this.addFieldError("statScheme.statSnapshotTime", this.getText("statScheme.snapshotTime.empty"));
				}
				if(null != statScheme.getStatScheduledTime()) {
					this.addFieldError("statScheme.statScheduledTime", this.getText("statScheme.scheduledTime.unabled"));
				}
			}
		}
	}
	
	/**
	 * 更新统计分析计划
	 * @return String
	 */
	@Validations(requiredStrings = { @RequiredStringValidator(type = ValidatorType.SIMPLE, 
			fieldName = "statScheme.code", shortCircuit = true, key = "statScheme.code.empty"), 
			@RequiredStringValidator(type = ValidatorType.SIMPLE, 
			fieldName = "statScheme.name", shortCircuit = true, key = "statScheme.name.empty"),
			@RequiredStringValidator(type = ValidatorType.SIMPLE, 
			fieldName = "statScheme.rptDataType", shortCircuit = true, key = "statScheme.rptDataType.empty"),
			@RequiredStringValidator(type = ValidatorType.SIMPLE, 
			fieldName = "statScheme.statField", shortCircuit = true, key = "statScheme.statField.empty")})
	public String updateStatScheme() {
		long stime = System.currentTimeMillis();
		
		try {
			statSchemeService.updateStatScheme(statScheme);
			
			// 统计分析计划配置任务处理
			executor.execute(new StatSchemeProcessTask(qtzJobScheduler,
					statSchemeService, statScheme.getId(),
					StatSchemeTaskType.AddOrUpdate));
			
			logger.debug("StatSchemeAction.updateStatScheme invoke success, costTime={}ms"
					, new Object[]{System.currentTimeMillis() - stime});
			
		} catch(DBException dbex) {
			logger.error("StatSchemeAction.updateStatScheme found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("StatSchemeAction.updateStatScheme found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 更新统计分析计划业务校验
	 */
	public void validateUpdateStatScheme() {
		if(null != statScheme) {
			if(statScheme.getStatStrategyCode() == 11) {
				if(null == statScheme.getStatScheduledTime()) {
					this.addFieldError("statScheme.statScheduledTime", this.getText("statScheme.scheduledTime.empty"));
				}
				if(null != statScheme.getStatSnapshotTime()) {
					this.addFieldError("statScheme.statSnapshotTime", this.getText("statScheme.snapshotTime.unabled"));
				}
			} else if(statScheme.getStatStrategyCode() == 12) {
				if(null == statScheme.getStatSnapshotTime()) {
					this.addFieldError("statScheme.statSnapshotTime", this.getText("statScheme.snapshotTime.empty"));
				}
				if(null != statScheme.getStatScheduledTime()) {
					this.addFieldError("statScheme.statScheduledTime", this.getText("statScheme.scheduledTime.unabled"));
				}
			}
		}
	}
	
	/**
	 * 删除统计分析计划
	 * @return String
	 */
	public String removeStatScheme() {
		long stime = System.currentTimeMillis();
		
		try {
			Integer statSchemeId = statScheme.getId();
			statSchemeService.removeStatSchemeById(statSchemeId);
			
			// 统计分析计划配置任务处理
			executor.execute(new StatSchemeProcessTask(qtzJobScheduler,
					statSchemeService, statSchemeId, StatSchemeTaskType.Remove));
			
			logger.debug("StatSchemeAction.removeStatScheme invoke success, costTime={}ms"
					, new Object[]{System.currentTimeMillis() - stime});
			
		} catch(DBException dbex) {
			logger.error("StatSchemeAction.removeStatScheme found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("StatSchemeAction.removeStatScheme found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
}
