package com.iuni.dp.persist.sys.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.sys.dao.UserDAO;
import com.iuni.dp.persist.sys.model.Menu;
import com.iuni.dp.persist.sys.model.User;

@Repository("userDao")
public class UserDAOImpl extends BaseDaoImpl<User, Serializable> implements UserDAO {
	
	static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class.getName());

	public Long insertUser(User user) {
		return (Long) this.insert(user, User.class.getSimpleName()+ ".insertUser");
	}

	public List<User> getAllUser() {
		return this.getAll(User.class.getSimpleName() + ".getAllUser");
	}

	public int updateUserPwd(User user) {
		return update(user, User.class.getSimpleName() + ".updatePwd");
	}

	public User getUser(long id) {
		return (User) this.getById(User.class.getSimpleName() + ".getUserById",id);
	}

	public User checkUserName(String username) {
		return (User) this.getObjectByProperty(User.class.getSimpleName()+ ".checkUserName", username);
	}

	public User checkOldPwd(User user) {
		return (User) getObjectByProperty(User.class.getSimpleName()+ ".checkOldPwd", user);
	}

	public User loginUser(User user) {
		return (User) this.getObjectByProperty(User.class.getSimpleName()+ ".getUserByLogin", user);
	}

	public List<User> getAllUserPage(User user) {
		return findAllObjectsByPage(User.class.getSimpleName() + ".getAllUser",user);
	}

	public Integer getAllUserPageCount(User user) {
		return findAllObjectsCount(User.class.getSimpleName()+ ".getAllUserCount", user);
	}

	public int chanageStatusAll(User user) {
		return update(user, User.class.getSimpleName()+ ".updateStatusAll");
	}

	public int chanageStatus(User user) {
		return update(user, User.class.getSimpleName()+ ".updateStatus");
	}

	public List<Menu> getAllMenuByUid(long uid) {
		return this.getAllObjects(Menu.class.getSimpleName()+ ".getAllMenuByUid", uid);
	}

	public Menu getParentMenu(Menu menu) {
		return (Menu) this.getObjectByProperty(Menu.class.getSimpleName()+ ".getParentMenu", menu);
	}

	public List<Menu> getAllChildMenus(Menu menu) {
		return this.getAllObjects(Menu.class.getSimpleName()+ ".getAllChildMenus", menu);
	}

	public int updateUser(User user) {
		return update(user, User.class.getSimpleName() + ".updateUser");
	}

	public User checkUpdateUserName(User user) {
		return (User) this.getObjectByProperty(User.class.getSimpleName()+ ".checkUpdateUserName", user);
	}

	public List<Menu> getAllMenus() {
		return this.getAllObjects(Menu.class.getSimpleName() + ".getAllMenu",new Menu());
	}
	
	public User getUserInfo(User user){
		return (User) getObjectByProperty(User.class.getSimpleName()+ ".getUserInfo", user);
	}

}
