package com.anbang.auth.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.anbang.auth.dao.IUserDao;
import com.anbang.auth.po.User;
import com.anbang.dao.impl.CommonDao;
@Repository("com.anbang.auth.dao.IUserDao")
public class UserDao extends CommonDao<User> implements IUserDao{

	@Override
	public User login(User u) {
		List<User> temp=getHibernateTemplate().findByExample(u);
		if(temp!=null&&temp.size()>0)
			return temp.get(0);
		return null;
	}

	@Override
	public User saveEpUser(User u) {
		u.setUserType("ep");
		this.saveOrUpdate(u);
		return u;
	}

	@Override
	public User getUserByEpId(String epId) {
		// TODO Auto-generated method stub
		List<User> temp=getHibernateTemplate().find("from User u where u.epId='"+epId+"'");
		if(temp!=null&&temp.size()>0)
			return (User) temp.get(0);
		return null;
	}



}
