package com.iuni.dp.admin.sys.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iuni.common.utils.MD5Util;
import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.persist.common.constants.TypeEnum;
import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.persist.sys.model.Menu;
import com.iuni.dp.persist.sys.model.User;
import com.iuni.dp.service.sys.constants.SysCons;
import com.iuni.dp.service.sys.service.LogService;
import com.iuni.dp.service.sys.service.UserService;
import com.iuni.dp.service.sys.service.ValidUsers;
 
public class UserAction extends BaseAction {
	
	/**
	 * @Description: msg="请先登录！ 
	 * 如果未登陆，LoginInterceptor会拦截请求并转向登陆页
	 * 如果已登陆，转向登陆页面
	 * @param 
	 * @return String
	 */
	public String index() {
		return SUCCESS;
	}

	/**
	 * @Description: 用户登录 
	 * @param 
	 * @return String
	 */
	public String login() {
		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		if (userSession != null) {
			return SUCCESS;
		} else if (StringUtils.isEmpty(user_name) || StringUtils.isEmpty(password)) {
			this.msg = TypeEnum.BusiExcEnum.LOGIN_NAME_PASSWORD_NOT_EMPTY.getValue();
			return INPUT;
		} else if (!(TypeEnum.SecurityCodeStateEnum.CODE_STATE_TEST.getKey()).equalsIgnoreCase(ParseProperties.getPropertiesValue(SysCons.TESTMODEL))
				&& session.getAttribute(SysCons.RANDOM) == null) {
			this.msg = TypeEnum.BusiExcEnum.SECURITY_CODE_INVALID.getValue();
			return INPUT;
		} else if(!(TypeEnum.SecurityCodeStateEnum.CODE_STATE_TEST.getKey()).equalsIgnoreCase(ParseProperties.getPropertiesValue(SysCons.TESTMODEL))
				 && !((String) session.getAttribute(SysCons.RANDOM)).equals(code)) {
				this.msg = TypeEnum.BusiExcEnum.SECURITY_CODE_ERROR.getValue();
				return INPUT;
		} else {
			try {
				User user = new User();
				user.setUser_name(user_name);
				user.setPassword(MD5Util.encode(MD5Util.encode(password)));
				user = userService.login(user);
				if (user != null) {
					if (user.getStatus() == Integer.valueOf(TypeEnum.UserStateEnum.USER_STATE_FREEZE.getKey())) {
						this.msg = TypeEnum.BusiExcEnum.USER_FREEZE.getValue();
						return INPUT;
					} else {
						session.setAttribute(SysCons.USER, user);
						session.setAttribute(SysCons.USER_ID, String.valueOf(user.getUser_id()));
						return SUCCESS;
					}
				} else {
					this.msg = TypeEnum.BusiExcEnum.LOGIN_NAME_PASSWORD_ERROR.getValue();
					return INPUT;
				}
			} catch (Exception e) {
				this.msg = TypeEnum.BusiExcEnum.LOGIN_EXCEPTION.getValue();
				return INPUT;
			}
	   }
	}
	
	/**
	 * @Description: 用户退出 
	 * @param 
	 * @return String
	 */
	public String logout() {
		HttpSession session = request.getSession();
		session.removeAttribute(SysCons.USER);
		session.invalidate();
		return SUCCESS;
	}
	
	// 取菜单
	public String findMenus() {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SysCons.USER);
		if (user == null) {
			return LOGIN;
		} else {
			menu = userService.findMenus(user.getUser_id());
			return "menu";
		}
	}

	public String updatePwd() {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		} else {
			msg = "请修改密码！";
			return SUCCESS;
		}
	}
	
	public String updateUser() {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		} else {
			User usr = this.userService.getUser(user_id);
			this.user_name = usr.getUser_name();
			this.password = "";
			this.mobile = usr.getMobile();
			this.phone = usr.getPhone();
			this.contactName = usr.getContact_name();
			this.user_type = usr.getUser_type();
			this.status = usr.getStatus();
			msg = "请修改用户信息！";
			return SUCCESS;
		}
	}
	
	public String updateUserResult() {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		User user = new User();
		user.setUser_id(user_id);
		user.setUser_name(user_name);
		user.setMobile(mobile);
		if (StringUtils.isNotEmpty(password)) {// 修改用户时，密码非必填项
			password = MD5Util.encode(password);
			password = MD5Util.encode(password);
			user.setPassword(password);
		}
		user.setPhone(phone);
		user.setContact_name(contactName);
		user.setStatus(status);
		user.setUser_type(user_type);
		user.setModifier(u.getUser_id());
		String msgprefix = "[修改用户状态] 操作者:" + u.getUser_name();
		if (this.userService.checkUpdateUserName(user) == null) {
			try {
				int cnt = this.userService.updateUser(user);
				if (cnt > 0) {
					msg = "[修改用户信息成功！]";
					logService.putLog2Memory(u.getUser_id(), u.getUser_name(),getIp(),
							msgprefix + " 修改用户成功，其用户信息为：" + user.toString(),
							TypeEnum.BusLogTypeEnum.USER_MANAGE);
					user_name = "";
					phone = "";
					contactName = "";
					this.getAllUserPage();
					return SUCCESS;
				} else {
					msg = "[修改用户信息失败！]";
					logService.putLog2Memory(u.getUser_id(), u.getUser_name(),
							getIp(), msgprefix + " 修改用户失败，请查看相关日志文件,其用户信息为:"+ user.toString(),
							TypeEnum.BusLogTypeEnum.USER_MANAGE);
					return INPUT;
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg = "修改用户信息产生异常！";
				logger.error(msg, e);
				logService.putLog2Memory(u.getUser_id(), u.getUser_name(),getIp(),
						msgprefix + " 修改用户失败,其用户信息为:" + user.toString()+ "</br> error:" + e.toString(),
						TypeEnum.BusLogTypeEnum.USER_MANAGE);
				return INPUT;
			}
		} else {
			msg = "此登录账号:" + user_name + "已经存在！";
			logService.putLog2Memory(u.getUser_id(), u.getUser_name(), getIp(),
					msgprefix + " 修改用户失败," + "此登录账号:" + user_name + "已经存在！",
					TypeEnum.BusLogTypeEnum.USER_MANAGE);
			return INPUT;
		}
	}
	
	public String updateResult() {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		pwd1 = MD5Util.encode(pwd1);
		pwd1 = MD5Util.encode(pwd1);
		user.setPassword(pwd1);
		String username = user.getUser_name();
		String msgprefix = "[修改密码] " + username + "修改密码";
		if (this.userService.checkOldPwd(user) == null) {
			msg = "原密码输入错误，请重新再输一次！";
			logService.putLog2Memory(user.getUser_id(), user.getUser_name(),
					getIp(), msgprefix + "失败，因" + msg,
					TypeEnum.BusLogTypeEnum.USER_MANAGE);
			return INPUT;
		} else {
			password = MD5Util.encode(password);
			password = MD5Util.encode(password);

			if (pwd1.equalsIgnoreCase(password)) {
				msg = "密码修改失败！新旧密码不能相同！";
				logService.putLog2Memory(user.getUser_id(),
						user.getUser_name(), getIp(),
						msgprefix + "失败，新旧密码不能相同",
						TypeEnum.BusLogTypeEnum.USER_MANAGE);
				return INPUT;
			}
			user.setPassword(password);
			user.setModifier(user.getUser_id());
			if (this.userService.updateUserPwd(user) > 0) {
				msg = "密码修改成功！";
				logService.putLog2Memory(user.getUser_id(),
						user.getUser_name(), getIp(), msgprefix + "成功",
						TypeEnum.BusLogTypeEnum.USER_MANAGE);
				return SUCCESS;
			} else {
				msg = "密码修改失败！";
				logService.putLog2Memory(user.getUser_id(),
						user.getUser_name(), getIp(), msgprefix + "失败,请查看日志文件",
						TypeEnum.BusLogTypeEnum.USER_MANAGE);
				return INPUT;

			}
		}
	}
	
	public String showUser(){		 
		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");	
		if(user==null){
			return "login";
		}else{	 				 		 
			return SUCCESS;			 
		}
	}
	
	public String getAllUser() {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (u == null) {
			return "login";
		} else {
			User user = new User();
			try {
				long start = (pageIndex - 1) * pageSize;
				long end = pageIndex * pageSize;
				user.setStart(start);
				user.setEnd(end);
				user.setUser_name("");
				user.setPhone("");
				user.setContact_name("");
				user.setMerchant_code("");
				// 区分管理员账号页面和商户账号账号页面
				user.setUser_type(this.user_type);
				setTotalCounts(this.userService.getAllUserPageCount(user));
				uList = this.userService.getAllUserPage(user);
				request.setAttribute("uList", uList);
				return SUCCESS;
			} catch (Exception ex) {
				msg = "分页查询产生异常！";
				logService.putLog2Memory(u.getUser_id(), u.getUser_name(),
						getIp(), msg, TypeEnum.BusLogTypeEnum.USER_MANAGE);
				return ERROR;
			}
		}
	}
	
	public String getAllUserPage() {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (u == null) {
			return "login";
		} else {
			User user = new User();
			try {
				long start = (pageIndex - 1) * pageSize;
				long end = pageIndex * pageSize;
				user.setStart(start);
				user.setSize(pageSize);
				user.setEnd(end);
				user.setUser_name(user_name);
				user.setPhone(phone);
				user.setContact_name(contactName);

				// 区分管理员账号页面和商户账号账号页面
				user.setUser_type(this.user_type);

				setTotalCounts(this.userService.getAllUserPageCount(user));
				uList = this.userService.getAllUserPage(user);
				request.setAttribute("uList", uList);
				return SUCCESS;
			} catch (Exception ex) {
				msg = "分页查询产生异常！";
				logService.putLog2Memory(u.getUser_id(), u.getUser_name(),
						getIp(), msg, TypeEnum.BusLogTypeEnum.USER_MANAGE);
				return ERROR;
			}
		}
	}
	
	public String changeAllStaus() {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		String user_ids = "";
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				user_ids += "," + ids[i];
			}
			if (user_ids.indexOf(",") == 0 && user_ids.length() >= 2) {
				user_ids = user_ids.substring(1);
			}
			if (status == SysCons.USER_STATUS_NORMAL) {
				status = SysCons.USER_STATUS_FROZEN;
			} else if (status == SysCons.USER_STATUS_FROZEN) {
				status = SysCons.USER_STATUS_NORMAL;
			}
			User user = new User();
			user.setStatus(status);
			user.setUser_ids(user_ids);
			user.setModifier(u.getUser_id());
			String msgprefix = "[修改用户状态] 操作者:" + u.getUser_name();
			String statustemp = "";
			if (status == 1) {
				statustemp = "正常";
			} else {
				statustemp = "冻结";
			}
			if (this.userService.chanageStatusAll(user) > 0) {
				msg = "批量修改用户状态成功！";
				// logService.putLog(msg, LogConstants.LOGTYPE_BIZLOG);
				logService.putLog2Memory(u.getUser_id(), u.getUser_name(),
						getIp(), msgprefix + " 批量修改用户状态成功！ 将下列用户【" + user_ids+ "】的状态更新为" + statustemp,
						TypeEnum.BusLogTypeEnum.USER_MANAGE);
				this.getAllUserPage();

				// 对内存中的冻结用户Map进行操作
				ValidUsers.addOrRemoveFrozenUser(ids, user);

				return SUCCESS;
			} else {
				msg = "批量修改用户状态失败！";
				logService.putLog2Memory(u.getUser_id(), u.getUser_name(),
						getIp(), msgprefix + " 批量修改用户状态失败！将下列用户【" + user_ids+ "】的状态更新为" + statustemp,
						TypeEnum.BusLogTypeEnum.USER_MANAGE);
				this.getAllUserPage();
				return INPUT;
			}
		} else {
			User user = new User();
			long start = (pageIndex - 1) * pageSize;
			long end = pageIndex * pageSize;
			user.setStart(start);
			user.setSize(pageSize);
			user.setEnd(end);
			user.setUser_name(user_name);
			user.setPhone(phone);
			user.setContact_name(contactName);
			setTotalCounts(this.userService.getAllUserPageCount(user));
			uList = this.userService.getAllUserPage(user);
			request.setAttribute("uList", uList);
			msg = "至少要选中一个要操作的帐号！";
			return ERROR;
		}
	}

	public String changeStaus() {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (status == SysCons.USER_STATUS_NORMAL) {
			status = SysCons.USER_STATUS_FROZEN;
		} else if (status == SysCons.USER_STATUS_FROZEN) {
			status = SysCons.USER_STATUS_NORMAL;
		}
		User user = new User();
		user.setUser_id(user_id);
		user.setStatus(status);
		user.setModifier(u.getUser_id());
		String statustemp = "";
		if (status == 1) {
			statustemp = "正常";
		} else {
			statustemp = "冻结";
		}
		String msgprefix = "[修改用户状态] 操作者:" + u.getUser_name();
		
		// 用户状态,1-正常;0-冻结
		if (this.userService.chanageStatus(user) > 0) {
			msg = "修改用户状态成功！";
			logService.putLog2Memory(u.getUser_id(), u.getUser_name(), getIp(),
					msgprefix + " 修改用户状态成功！(将用户ID:" + user_id + "的状态修改为："
							+ statustemp, TypeEnum.BusLogTypeEnum.USER_MANAGE);
			this.getAllUserPage();

			// 对内存中的冻结用户Map进行操作
			ValidUsers.addOrRemoveFrozenUser(user);
			return SUCCESS;
		} else {
			msg = "修改用户状态失败！";
			logService.putLog2Memory(u.getUser_id(), u.getUser_name(), getIp(),
					msgprefix + " 修改用户状态失败！(将用户ID:" + user_id + "的状态修改为："
							+ statustemp, TypeEnum.BusLogTypeEnum.USER_MANAGE);
			this.getAllUserPage();
			return INPUT;
		}
	}
	
	public String addUser(){ 
		return SUCCESS;
	}
	
	public String addUserResult() {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		User user = new User();
		user.setUser_name(user_name);
		user.setMobile(mobile);
		password = MD5Util.encode(password);
		password = MD5Util.encode(password);
		user.setPassword(password);
		user.setPhone(phone);
		user.setContact_name(contactName);
		user.setModifier(u.getUser_id());
		user.setCreator(u.getUser_id());
		user.setStatus(status);
		user.setUser_type(user_type);
		String msgprefix = "[添加账号] 操作者:" + u.getUser_name();
		if (this.userService.checkUserName(user_name) == null) {
			try {
				Long lon = this.userService.insertUser(user);
				if (lon > 0) {
					user_name = "";
					phone = "";
					contactName = "";
					getAllUserPage();
					msg = "添加账号成功！";
					logService.putLog2Memory(u.getUser_id(), u.getUser_name(),
							getIp(), msgprefix + " 添加" + user_name+ "账号成功, 其新增用户信息：" + user.toString(),
							TypeEnum.BusLogTypeEnum.USER_MANAGE);
					return LISTPAGE;
				} else {
					msg = "添加账号失败！";
					logService.putLog2Memory(u.getUser_id(), u.getUser_name(),
							getIp(), msgprefix + " 添加" + user_name+ "账号失败！ 其新增用户信息：" + user.toString(),
							TypeEnum.BusLogTypeEnum.USER_MANAGE);
					return INPUT;
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg = "添加账号产生异常！";
				logService.putLog2Memory(u.getUser_id(), u.getUser_name(),
						getIp(), msgprefix + " 添加" + user_name + "账号失败！error:"+ e.toString(),
						TypeEnum.BusLogTypeEnum.USER_MANAGE);
				return ERROR;
			}
		} else {
			msg = "此登录账号:" + user_name + "已经存在！";
			logService.putLog2Memory(u.getUser_id(), u.getUser_name(), getIp(),
					msgprefix + " 添加" + user_name + "账号失败！因 此登录账号:" + user_name
							+ "已经存在！", TypeEnum.BusLogTypeEnum.USER_MANAGE);
			return INPUT;
		}
	}
	
	public List<User> getuList() {
		return uList;
	}

	public void setuList(List<User> uList) {
		this.uList = uList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	} 

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getUser_type() {
		return user_type;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPwd1() {
		return pwd1;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
 
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LogService logService;
	
	private static final String LISTPAGE = "list"; 
	private static final long serialVersionUID = 1232048467870973533L;
	private static Logger logger = LoggerFactory.getLogger(UserAction.class);
	private List<User> uList;
	private String code;
	private String msg;
	private String user_name;
	private String password;
	private String [] ids;
    private String errMsg;
	private Menu menu;
	private String  mobile ;
	private String  phone ;
	private String contactName;
	private int  user_type;
	private int  status;//用户状态,1-正常;0-冻结
	private String pwd1;
	private  long  user_id;
}