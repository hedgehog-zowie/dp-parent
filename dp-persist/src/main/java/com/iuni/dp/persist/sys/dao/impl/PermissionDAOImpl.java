package com.iuni.dp.persist.sys.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.sys.dao.PermissionDAO;
import com.iuni.dp.persist.sys.model.Permission;

@Repository("permissionDao")
public class PermissionDAOImpl extends BaseDaoImpl<Permission, Serializable>implements PermissionDAO {
	
	static Logger logger = LoggerFactory.getLogger(PermissionDAOImpl.class.getName());

	public int deletePermissionByUid(long uid) {
		return delete(uid, Permission.class.getSimpleName()+ ".deletePermissionById");
	}

	public List<Permission> getAllPermission(long uid) {
		return findAllObjectsByPage(Permission.class.getSimpleName()+ ".getPermissionByUId", uid);
	}

	public Permission getPermission(Permission perm) {
		return (Permission) getObjectByProperty(Permission.class.getSimpleName() + ".checkPermission", perm);
	}

	public void insertPermission(Permission perm) {
		insert(perm, Permission.class.getSimpleName()+ ".insertPermission");
	}

}
