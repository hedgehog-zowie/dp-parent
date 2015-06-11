package com.iuni.dp.admin.sys.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.persist.common.constants.TypeEnum;
import com.iuni.dp.persist.sys.model.Menu;
import com.iuni.dp.persist.sys.model.Permission;
import com.iuni.dp.persist.sys.model.System;
import com.iuni.dp.persist.sys.model.User;
import com.iuni.dp.service.sys.service.LogService;
import com.iuni.dp.service.sys.service.MenuService;
import com.iuni.dp.service.sys.service.SystemService;
 
public class MenuAction extends BaseAction {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private LogService logService;
	
	private List<System> sysList;
	private List<Menu> mList;
	private List<Menu>userList;
	private int pid;
	private String msg;
	private int id;
	private String name;
	private String url;
	private String user_type;
	private String user_name;
	private long u_id;
	private String parent_id = Integer.toString(Menu.ROOT_ID);
	private String sysId = System.CUR_SYS_ID+"";
	private static final long serialVersionUID = 1232048467870973533L;
	static Logger logger = LoggerFactory.getLogger(MenuAction.class);
	
	public List<System> getSysList() {
		return sysList;
	}
	public void setSysList(List<System> sysList) {
		this.sysList = sysList;
	}
	public List<Menu> getUserList() {
		return userList;
	}
	public void setUserList(List<Menu> userList) {
		this.userList = userList;
	}
	public List<Menu> getmList() {
		return mList;
	}
	public void setmList(List<Menu> mList) {
		this.mList = mList;
	}
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	/**
	 * 添加，修改菜单功能等等；
	 */
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getMsg() {
		return msg;
	} 

	public void setMsg(String msg) {
		this.msg = msg;
	}
	 
	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getU_id() {
		return u_id;
	}

	public void setU_id(long uId) {
		u_id = uId;
	}
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String userName) {
		user_name = userName;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parentId) {
		parent_id = parentId;
	}

	public String updateMenu(){
		Menu menu=menuService.getMenu(id);	
		setName(menu.getName());
		setUrl(menu.getUrl());
		setId(menu.getId());
		setParent_id(Integer.toString(menu.getParent_id()));
		setPid(menu.getParent_id());
		mList=menuService.getAllMenu(new Menu());
		request.setAttribute("mList",mList);
		return SUCCESS;
		 
	}
	public String updateMenuResult(){
		HttpSession session=request.getSession();
		User u = (User) session.getAttribute("user");
		Menu menu=new Menu(); 
		menu.setName(name);
		menu.setUrl(url);
		menu.setId(id);
		menu.setParent_id(Integer.parseInt(parent_id)); 
		menu.setModifier(u.getUser_id());
		String msgprefix="[修改菜单] 操作者:"+u.getUser_name();
		try {
			menuService.updateMenuName(menu);	
			msg="修改菜单成功！";
			logService.putLog2Memory(u.getUser_id(), u.getUser_name(), getIp(), msgprefix+"修改"+name+"成功，其菜单为信息："+menu.toString(), TypeEnum.SysLogTypeEnum.INSYSLOGSUCCESS);
			return SUCCESS;		 
		} catch (Exception e) {
			e.printStackTrace();
			msg="修改菜单产生异常！";
			logService.putLog2Memory(u.getUser_id(), u.getUser_name(), getIp(), msgprefix+"修改"+name+"失败，error"+e.toString(), TypeEnum.SysLogTypeEnum.INSYSLOGFAIL);
			return INPUT;
		}  
	}
	
	public String showPermission(){	
		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null){
			return LOGIN;
		}else{
			Menu menu=new Menu();
			mList=menuService.getAllMenu(menu);
			userList=menuService.getUserMenus(u_id);
			List<Permission> userMenuList=new ArrayList<Permission>();
			for(Menu m : userList){
				Permission perm=new Permission();
				perm.setM_id(m.getId());
				userMenuList.add(perm);
			}	
			request.setAttribute("mList",mList);
			request.setAttribute("userMenuList",userMenuList);
			msg=StringUtils.isNotEmpty(user_name)?"查看账号为："+user_name+"的权限配置：" : "给当前用户:"+user.getUser_name()+"进行授权";
			//logService.putLog2Memory(user.getUser_id(), user.getUser_name(), getIp(), msg, LogType.BusLogTypeEnum.USER_MANAGE);
			return SUCCESS;
		}
	}
	
	public String viewPermission(){	
		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null){
			return LOGIN;
		}else{
			Menu menu=new Menu();
			mList=menuService.getAllMenu(menu);
			request.setAttribute("mList",mList);
			msg="查看账号为："+user_name+"的权限配置：";
			//logService.putLog2Memory(user.getUser_id(), user.getUser_name(), getIp(), msg, LogType.BusLogTypeEnum.USER_MANAGE);
			return SUCCESS;
		}
	}
	 
	public String getAllMenu(){		
		Menu menu=new Menu();
		HttpSession session=request.getSession();
		User u = (User) session.getAttribute("user");
		if(u==null){
			return LOGIN;
		}else{
			try{
				mList=menuService.getAllMenu(menu);
				request.setAttribute("mList",mList);
				return SUCCESS;
			}catch(Exception ex){
				msg="分页查询产生异常！"+ex.getMessage();
				logService.putLog2Memory(u.getUser_id(), u.getUser_name(), getIp(), msg, TypeEnum.BusLogTypeEnum.USER_MANAGE);
				return ERROR;
			}
		}
	}
	
	@SuppressWarnings("unused")
	public String deleteMenu(){
		HttpSession session=request.getSession();
		User u = (User) session.getAttribute("user");
		String msgprefix="[删除菜单] 操作者:"+u.getUser_name();
		if (u == null) {
			return LOGIN;
		} else {
			if (menuService.deleteMenu(id) > 0) {
				msg = "删除菜单成功！";
				mList = menuService.getAllMenu(new Menu());
				request.setAttribute("mList", mList);
				logService.putLog2Memory(u.getUser_id(), u.getUser_name(),getIp(), msgprefix + " 删除菜单成功！ 菜单id:" + id,TypeEnum.SysLogTypeEnum.INSYSLOGSUCCESS);
				return SUCCESS;
			} else {
				msg = "删除菜单失败！";
				logService.putLog2Memory(u.getUser_id(), u.getUser_name(),getIp(), msgprefix + " 删除菜单失败！ 菜单id:" + id,TypeEnum.SysLogTypeEnum.INSYSLOGFAIL);
				mList = menuService.getAllMenu(new Menu());
				request.setAttribute("mList", mList);
				return INPUT;
			}
		}
	}   
	
	public String addMenu(){
		HttpSession session=request.getSession();
		User u = (User) session.getAttribute("user");
		if(u==null){
			return LOGIN;
		}else{
			mList=menuService.getAllMenu(new Menu());
			sysList = systemService.getSystemList();
			request.setAttribute("mList",mList);
			request.setAttribute("sysList",sysList);
			return SUCCESS;
		}
	}  
	
	public String addMenuResult(){
		HttpSession session=request.getSession();
		User u = (User) session.getAttribute("user");
		Menu menu=new Menu(); 
		menu.setName(name);
		menu.setUrl(url);
		menu.setId(id);
		menu.setParent_id(Integer.parseInt(parent_id)); 
		menu.setSysId(Integer.parseInt(sysId));
		menu.setModifier(u.getUser_id());
		menu.setCreator(u.getUser_id());
		String msgprefix="[新增菜单] 操作者:"+u.getUser_name();
		try {
			if(menuService.checkMenuId(id,sysId)!=null){
				msg="该菜单ID："+id+"已经存在！";
				//logService.putLog2Memory(u.getUser_id(), u.getUser_name(), getIp(), msg, LogType.SysLogTypeEnum.INSYSLOGFAIL);
				return INPUT;
			}else{
				if(menuService.checkMenuName(name,sysId)!=null){
					msg="该菜单名称："+name+"已经存在！";
				//	logService.putLog2Memory(u.getUser_id(), u.getUser_name(), getIp(), msg, LogType.SysLogTypeEnum.INSYSLOGFAIL);
					return INPUT;		
				}else{					
						menuService.insertMenu(menu);	
						logService.putLog2Memory(u.getUser_id(), u.getUser_name(), getIp(), msgprefix+" 新增"+name+"菜单成功", TypeEnum.SysLogTypeEnum.INSYSLOGSUCCESS);
						msg="添加菜单成功！";
						return SUCCESS;			 
					}
			    }
			} catch (Exception e) {
			e.printStackTrace();
			msg="添加菜单产生异常!";
			logService.putLog2Memory(u.getUser_id(), u.getUser_name(), getIp(), msgprefix+"添加菜单"+name+"失败！菜单详情为:"+menu.toString()+",error:"+e.toString(), TypeEnum.SysLogTypeEnum.INSYSLOGFAIL);
			return INPUT;
			
		} 
	}
}