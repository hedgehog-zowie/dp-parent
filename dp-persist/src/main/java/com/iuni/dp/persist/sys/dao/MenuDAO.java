package com.iuni.dp.persist.sys.dao;

import java.io.Serializable;
import java.util.List;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.sys.model.Menu;

public interface MenuDAO extends BaseDao<Menu, Serializable> {
	
	public void insertMenu(Menu menu);

	public List<Menu> getAllMenu();

	public Menu getMenu(int id);

	public Menu checkMenuId(int menuId,String sysId);

	public Menu checkMenuName(String menuName,String sysId);

	public int updateMenuName(Menu menu);

	public int deleteMenu(int id);

	public List<Menu> getAllMenu(Menu menu);

	public Integer getAllMenuCount(Menu menu);

	public List<Menu> getUserMenus(long uid);
	
	public List<Menu> getMenuList(String[] sysIds,String optId);
	
}
