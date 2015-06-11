package com.iuni.dp.persist.sys.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.sys.dao.MenuDAO;
import com.iuni.dp.persist.sys.model.Menu;

@Repository("menuDao")
public class MenuDAOImpl extends BaseDaoImpl<Menu, Serializable> implements MenuDAO {

	private static Logger logger = LoggerFactory.getLogger(MenuDAOImpl.class);
	
	public void insertMenu(Menu menu) {
		this.insert(menu, Menu.class.getSimpleName() + ".insertMenu");
	}

	public List<Menu> getAllMenu() {
		return this.getAll(Menu.class.getSimpleName() + ".getAllMenu");
	}

	public int deleteMenu(int id) {
		return this.delete(id, Menu.class.getSimpleName() + ".deleteMenu");
	}

	public int updateMenuName(Menu menu) {
		return update(menu, Menu.class.getSimpleName() + ".updateMenuName");
	}

	public Menu getMenu(int id) {
		return (Menu) this.getById(Menu.class.getSimpleName() + ".getMenuById",id);
	}

	public List<Menu> getAllMenu(Menu menu) {
		return findAllObjectsByPage(Menu.class.getSimpleName() + ".getAllMenu",menu);
	}

	public List<Menu> getUserMenus(long uid) {
		return findAllObjectsByPage(Menu.class.getSimpleName()+ ".getUserMenus", uid);
	}

	public Integer getAllMenuCount(Menu menu) {
		return  findAllObjectsCount(Menu.class.getSimpleName()+ ".getAllMenuCount", menu);
	}

	@Override
	public Menu checkMenuId(int menuId,String sysId) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("id", menuId);
		paramsMap.put("sys_id", sysId);
		return (Menu) this.getObjectByProperty(Menu.class.getSimpleName()+ ".checkMenuId", paramsMap);
	}

	@Override
	public Menu checkMenuName(String menuName,String sysId) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("name", menuName);
		paramsMap.put("sys_id", sysId);
		return (Menu)getObjectByProperty(Menu.class.getSimpleName()+ ".checkMenuName", paramsMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> getMenuList(String[] sysIds,String optId) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("sys_ids", sysIds);
		paramsMap.put("u_id", optId);
		return (List<Menu>)getSqlMapClientTemplate().queryForList(Menu.class.getSimpleName()+ ".getMenuListBySysIdAndOptId", paramsMap);
	}

}
