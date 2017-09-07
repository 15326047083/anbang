package com.anbang.auth.dao;

import com.anbang.auth.po.User;
import com.anbang.dao.ICommonDao;

public interface IUserDao extends ICommonDao<User>{
	public User login(User u);
	public User saveEpUser(User u);
	public User getUserByEpId(String epId);
}
