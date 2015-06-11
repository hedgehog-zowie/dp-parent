package com.iuni.dp.persist.sys.dao;

import java.io.Serializable;
import java.util.List;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.sys.model.Menu;
import com.iuni.dp.persist.sys.model.User;

public interface UserDAO extends BaseDao<User, Serializable> {
	
	public User checkUserName(String username);

	public User checkUpdateUserName(User user);

	public List<Menu> getAllMenuByUid(long uid);

	public Long insertUser(User user);

	public List<User> getAllUser();

	public User getUser(long id);

	public int updateUserPwd(User user);

	public int updateUser(User user);

	public User checkOldPwd(User user);

	public User loginUser(User user);

	public int chanageStatus(User user);

	public int chanageStatusAll(User user);

	public List<User> getAllUserPage(User user);

	public Integer getAllUserPageCount(User user);

	public Menu getParentMenu(Menu menu);

	public List<Menu> getAllChildMenus(Menu menu);

	public List<Menu> getAllMenus();
	
	public User getUserInfo(User user);
	
}
