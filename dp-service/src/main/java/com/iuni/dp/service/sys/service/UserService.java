package com.iuni.dp.service.sys.service;

import java.util.List;

import com.iuni.dp.persist.sys.model.Menu;
import com.iuni.dp.persist.sys.model.User;

public interface UserService {
	public User checkUserName(String username);

	public Long insertUser(User user);

	public List<User> getAllUser();

	public User getUser(long id);

	public int updateUser(User user);

	public User checkUpdateUserName(User user);

	public User login(User user);

	public User checkOldPwd(User user);

	public int updateUserPwd(User user);

	public User authenticate(String username, String password);

	public int chanageStatus(User user);

	public int chanageStatusAll(User user);

	public List<User> getAllUserPage(User user);

	public Integer getAllUserPageCount(User user);

	public List<Menu> getAllMenuByUid(long uid);

	public Menu findMenus(long userID);

	public Menu findAllMenu();

	public Menu getParentMenu(Menu menu);

	public List<Menu> getAllChildMenus(Menu menu);
}
