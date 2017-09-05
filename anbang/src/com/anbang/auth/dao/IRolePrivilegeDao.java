package com.anbang.auth.dao;

import java.util.Set;

import com.anbang.auth.po.RolePrivilege;
import com.anbang.dao.ICommonDao;

public interface IRolePrivilegeDao extends ICommonDao<RolePrivilege>{
	public Set<String> getPrivilegeByRoleName(String ... roleName);
}
