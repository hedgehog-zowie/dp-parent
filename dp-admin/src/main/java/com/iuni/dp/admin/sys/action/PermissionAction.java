package com.iuni.dp.admin.sys.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.persist.common.constants.TypeEnum;
import com.iuni.dp.persist.sys.model.Menu;
import com.iuni.dp.persist.sys.model.Permission;
import com.iuni.dp.persist.sys.model.User;
import com.iuni.dp.service.sys.service.LogService;
import com.iuni.dp.service.sys.service.MenuService;
import com.iuni.dp.service.sys.service.PermissionService;
 
public class PermissionAction extends BaseAction {
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private LogService logService; 
	
	@Autowired
	private MenuService menuService;
	
	private static final long serialVersionUID = 1232048467870973533L;
	static final Logger logger = LoggerFactory.getLogger(PermissionAction.class);
	private String [] ids;
	private Long u_id;
	private String user_type;
	private String user_name;
	private String msg;
 
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
 
	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public long getU_id() {
		return u_id;
	}

	public void setU_id(long uId) {
		u_id = uId;
	}

	public String getMsg() {
		return msg;
	} 

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * 菜单授权
	 * @author andy
	 * @return String
	 */
	public String updatePermission() {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String msgprefix = "[权限] 操作者:" + user.getUser_name();
		u_id = (u_id != null) ? u_id : user.getUser_id();
		try {
			Permission perm = new Permission();
			if (permissionService.getPermission(perm) != null) {
				msg = "原权限选择错误，请重新再选择一次！";
				// logService.putLog2Memory(user.getUser_id(),
				// user.getUser_name(), getIp(), msg,
				// LogType.BusLogTypeEnum.USER_MANAGE);
				return INPUT;
			} else {
				permissionService.deletePermissionByUid(u_id);
				for (int i = 0; i < ids.length; i++) {
					int mid = Integer.parseInt(ids[i]);
					perm.setM_id(mid);
					perm.setU_id(u_id);
					perm.setCreator(u_id);
					perm.setModifier(u_id);
					permissionService.insertPermission(perm);
				}
				msg = user_name + "的权限配置成功！";
				logService.putLog2Memory(user.getUser_id(),user.getUser_name(), getIp(), msgprefix+ this.user_name + "的权限配置成功！,其权限ids：" + ids,TypeEnum.BusLogTypeEnum.USER_MANAGE);
				show();
				return SUCCESS;
			}
		} catch (Exception ex) {
			msg = "权限配置失败！" + ex.getMessage();
			logService.putLog2Memory(user.getUser_id(), user.getUser_name(),getIp(), msgprefix + this.user_name + "的权限配置失败！,其权限ids："+ ids + "</br>error:" + ex.toString(),TypeEnum.BusLogTypeEnum.USER_MANAGE);
			return INPUT;
		}
	}
	
	private void show() {
		Menu menu = new Menu();
		List<Menu> mList = menuService.getAllMenu(menu);
		List<Menu> userList = menuService.getUserMenus(u_id);
		List<Permission> userMenuList = new ArrayList<Permission>();
		for (Menu m : userList) {
			Permission perm = new Permission();
			perm.setM_id(m.getId());
			userMenuList.add(perm);
		}
		request.setAttribute("mList", mList);
		request.setAttribute("userMenuList", userMenuList);
	}
	 
}