package com.iuni.dp.service.sys.service;

import java.util.List;

import com.iuni.dp.persist.sys.model.Menu;

public interface MenuService {
	
	/**
	 * 删除当前菜单及其当前菜单所有子菜单
	 * @param id
	 * @return
	 */
	public int deleteMenu(int id);
	
	public void insertMenu(Menu menu);

	public List<Menu> getAllMenu();

	public Menu getMenu(int id);

	public Menu checkMenuId(int menuId,String sysId);

	public Menu checkMenuName(String menuName,String sysId);

	public int updateMenuName(Menu menu);

	public List<Menu> getAllMenu(Menu menu);

	public List<Menu> getUserMenus(long uid);

	public Integer getAllMenuCount(Menu menu);
	
}
