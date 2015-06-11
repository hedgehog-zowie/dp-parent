package com.iuni.dp.service.sys.service;

import java.util.List;

import com.iuni.dp.persist.sys.model.Permission;

public interface PermissionService {
	
	public void insertPermission(Permission perm);

	public List<Permission> getAllPermission(long uid);

	public Permission getPermission(Permission perm);

	public int deletePermissionByUid(long uid);
	
}
