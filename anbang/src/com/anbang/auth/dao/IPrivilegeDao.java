package com.anbang.auth.dao;
import java.util.Set;

import com.anbang.auth.po.Privilege;
import com.anbang.dao.ICommonDao;
public interface IPrivilegeDao extends ICommonDao<Privilege>{
	public Set<String> getPrivilegeByUserId(String userId);
	
}
