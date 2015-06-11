package com.iuni.dp.admin.sys.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.persist.common.constants.TypeEnum;
import com.iuni.dp.persist.sys.model.SysParam;
import com.iuni.dp.persist.sys.model.User;
import com.iuni.dp.service.sys.service.LogService;
import com.iuni.dp.service.sys.service.SysParamService;

/**
 * 系统参数的查询和删除
 * 
 * @author Liangyongxu
 * 
 */
@Controller("sysParamAction")
@Scope("prototype")
public class SysParamAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4967698717635146964L;

	private final static Logger LOGGER = LoggerFactory.getLogger(SysParamAction.class);

	@Autowired
	private SysParamService sysParamService;

	@Autowired
	private LogService logService;

	private List<SysParam> sysParamList;

	private String paramName;// key

	private String name;// key

	private SysParam sysParam;

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public SysParam getSysParam() {
		return sysParam;
	}

	public void setSysParam(SysParam sysParam) {
		this.sysParam = sysParam;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public SysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(SysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public List<SysParam> getSysParamList() {
		return sysParamList;
	}

	public void setSysParamList(List<SysParam> sysParamList) {
		this.sysParamList = sysParamList;
	}

	public String showSysParam() {
		List<SysParam> list = sysParamService.getSysParams(paramName);
		this.sysParamList = list;
		return SUCCESS;
	}

	// 进入增加页面
	public String addSysParam() {
		return SUCCESS;
	}

	// 进入修改页面前初始化数据
	public String preUpdateSysParam() {
		this.sysParam = sysParamService.getSysParamsByParamName(name);
		return SUCCESS;
	}

	public String saveSysParam() {
		msg = "添加成功！";
		String msgprefix="";
		try {
			User user = (User) request.getSession().getAttribute("user");
			msgprefix="[系统参数] 操作者:"+user.getUser_name();
			sysParam.setCreateor(user.getUser_id());
			sysParamService.insertSysParam(sysParam);
			
			logService.putLog2Memory(user.getUser_id(), user.getUser_name(),
					request.getRemoteHost(),
					msgprefix+"增加系统参数[paramName：" + sysParam.getParamName()
							+ ",paramVal:" + sysParam.getParamVal() + "]",
							TypeEnum.SysLogTypeEnum.INSYSLOGSUCCESS);
			
		} catch (Exception e) {
			LOGGER.error("新增系统配置参数失败", e);
			msg = "添加失败！";
			logService.putLog(msgprefix+"新增系统配置参数失败,系统参数[paramName：" + sysParam.getParamName()
							+ ",paramVal:" + sysParam.getParamVal() + "],error:"+e.toString(), TypeEnum.SysLogTypeEnum.INSYSLOGFAIL);
		}
		showSysParam();
		return SUCCESS;
	}

	public String updateSysParam() {
		msg = "更新成功！";
		String msgprefix="";
		try {
			User user = (User) request.getSession().getAttribute("user");
			msgprefix="[系统参数] 操作者:"+user.getUser_name();
			sysParam.setModifier(user.getUser_id());
			sysParamService.updateSysParam(sysParam);
			
			logService.putLog2Memory(user.getUser_id(), user.getUser_name(),
					request.getRemoteHost(),
					msgprefix+" 修改系统参数[paramName：" + sysParam.getParamName()
							+ ",paramVal:" + sysParam.getParamVal() + "]",
							TypeEnum.SysLogTypeEnum.INSYSLOGSUCCESS);

		} catch (Exception e) {
			LOGGER.error("更新系统配置参数失败", e);
			msg = "更新失败！";
			logService.putLog(msgprefix+" 更新系统配置参数失败,系统参数[paramName：" + sysParam.getParamName()
					+ ",paramVal:" + sysParam.getParamVal() + "],error:"+e.toString(), TypeEnum.SysLogTypeEnum.INSYSLOGFAIL);
		}
		showSysParam();
		return SUCCESS;
	}

}
