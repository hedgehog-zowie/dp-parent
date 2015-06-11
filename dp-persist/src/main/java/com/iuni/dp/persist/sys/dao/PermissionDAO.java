package com.iuni.dp.persist.sys.dao;

import java.io.Serializable;
import java.util.List;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.sys.model.Permission;

public interface PermissionDAO extends BaseDao<Permission, Serializable> {
	
	public void insertPermission(Permission perm);

	public List<Permission> getAllPermission(long uid);

	public Permission getPermission(Permission perm);

	/**
	 * 根据用户id删除权限信息
	 * @param uid
	 * @return
	 */
	public int deletePermissionByUid(long uid);
}
