package com.iuni.dp.service.sys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.sys.dao.PermissionDAO;
import com.iuni.dp.persist.sys.model.Permission;
import com.iuni.dp.service.sys.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService{
	
	final static Logger LOGGER = LoggerFactory.getLogger(PermissionServiceImpl.class);
	
	@Autowired
	private PermissionDAO permissionDao ;

	public int deletePermissionByUid(long uid) {
		return permissionDao.deletePermissionByUid(uid);
	}

	public List<Permission> getAllPermission(long uid) {
		return permissionDao.getAllPermission(uid);
	}

	public Permission getPermission(Permission perm) {
		return permissionDao.getPermission(perm);
	}

	public void insertPermission(Permission perm) {
		permissionDao.insertPermission(perm);
	}
	
}
