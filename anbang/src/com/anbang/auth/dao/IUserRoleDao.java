package com.anbang.auth.dao;

import java.util.Set;

import com.anbang.auth.po.Role;
import com.anbang.auth.po.User;
import com.anbang.auth.po.UserRole;
import com.anbang.dao.ICommonDao;

public interface IUserRoleDao extends ICommonDao<UserRole>{
	public Set<String> getRoleByUserId(String userId);

}
