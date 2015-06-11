package com.iuni.dp.service.sys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.sys.dao.MenuDAO;
import com.iuni.dp.persist.sys.model.Menu;
import com.iuni.dp.service.sys.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuDAO menuDao ;
	
	final static Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);
	 
	public void insertMenu(Menu menu){
		menuDao.insertMenu(menu);
	}

	public List<Menu> getAllMenu() {
		return menuDao.getAllMenu();
	}

	public Menu getMenu(int id) {
		return menuDao.getMenu(id);
	}
	
	public List<Menu> getAllMenu(Menu menu){
		return menuDao.getAllMenu(menu);
	}
	
	public Integer getAllMenuCount(Menu menu){
		return menuDao.getAllMenuCount(menu);
	}

	public int updateMenuName(Menu menu) {
		return menuDao.updateMenuName(menu);
	}
	
	public int deleteMenu(int id) {
		return menuDao.deleteMenu(id);
	}
	
	@Override
	public Menu checkMenuId(int menuId,String sysId) {
		return menuDao.checkMenuId(menuId, sysId);
	}

	@Override
	public Menu checkMenuName(String menuName,String sysId) {
		return menuDao.checkMenuName(menuName, sysId);
	}

	public List<Menu> getUserMenus(long uid) {
		return menuDao.getUserMenus(uid);
	}
	
}
