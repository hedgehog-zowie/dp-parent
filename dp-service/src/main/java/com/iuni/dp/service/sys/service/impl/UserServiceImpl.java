package com.iuni.dp.service.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.sys.dao.UserDAO;
import com.iuni.dp.persist.sys.model.Menu;
import com.iuni.dp.persist.sys.model.User;
import com.iuni.dp.service.sys.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	public Long insertUser(User user) {
		return userDao.insertUser(user);
	}

	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	public User getUser(long id) {
		return userDao.getUser(id);
	}

	public List<Menu> getAllMenuByUid(long uid) {
		return userDao.getAllMenuByUid(uid);
	}

	public Menu getParentMenu(Menu menu) {
		return userDao.getParentMenu(menu);
	}

	public List<Menu> getAllChildMenus(Menu menu) {
		return userDao.getAllChildMenus(menu);
	}

	public List<Menu> findAllMenus() {
		return userDao.getAllMenus();
	}
	
	public User authenticate(String username, String password) {
		return new User();
	}
	
	public User login(User user) {
		return userDao.loginUser(user);
	}

	public int chanageStatus(User user) {
		return userDao.chanageStatus(user);
	}

	public int chanageStatusAll(User user) {
		return userDao.chanageStatusAll(user);
	}

	public List<User> getAllUserPage(User user) {
		return userDao.getAllUserPage(user);
	}

	public Integer getAllUserPageCount(User user) {
		return userDao.getAllUserPageCount(user);
	}

	public int updateUserPwd(User user) {
		return userDao.updateUserPwd(user);
	}

	public User checkOldPwd(User user) {
		return userDao.checkOldPwd(user);
	}

	public User checkUserName(String username) {
		return userDao.checkUserName(username);
	}

	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	public User checkUpdateUserName(User user) {
		return userDao.checkUpdateUserName(user);
	}

	public Menu findAllMenu() {
		Menu rootMenu = new Menu();
		rootMenu.setId(Menu.ROOT_ID);
		rootMenu.setName(Menu.ROOT_NAME);
		List<Menu> mlist = this.findAllMenus();
		for (Menu menu : mlist) {
			menu.setParentMenu(rootMenu);
			rootMenu.addChildMenu(menu);
		}
		return rootMenu;
	}

	public Menu findMenus(long userID) {
		Menu rootMenu = new Menu();
		rootMenu.setId(Menu.ROOT_ID);
		rootMenu.setName(Menu.ROOT_NAME);
		Map<String, Menu> parentMap = new HashMap<String, Menu>();
		List<Menu> mlist = this.getAllMenuByUid(userID);
		for (Menu menu : mlist) {
			Menu parent = this.getParentMenu(menu);
			if (parent != null) {
				parentMap.put(parent.getId() + "", parent);
			} else {
				parentMap.put(menu.getId() + "", menu);
			}
		}
		for (Menu menu : parentMap.values()) {
			menu.setParentMenu(rootMenu);
			List<Menu> childList = this.getAllChildMenus(menu);
			for (Menu m : childList) {
				m.setParentMenu(menu);
				menu.addChildMenu(m);
			}
			rootMenu.addChildMenu(menu);
		}
		return rootMenu;
	}
	
}
