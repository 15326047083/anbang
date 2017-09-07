package com.anbang.auth.dao.impl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.anbang.auth.dao.IUserRoleDao;
import com.anbang.auth.po.Role;
import com.anbang.auth.po.UserRole;
import com.anbang.dao.impl.CommonDao;

@Repository("com.anbang.auth.dao.IUserRoleDao")
public class RoleUserDao extends CommonDao<UserRole> implements IUserRoleDao{

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getRoleByUserId(final String userId) {
		// TODO Auto-generated method stub
		List<String> temp=getHibernateTemplate().executeFind(new HibernateCallback<List>() {

			@Override
			public List doInHibernate(Session arg0) throws HibernateException, SQLException {
				Query q = arg0.createQuery("select ur.roleId from UserRole ur where ur.userId=(:userId)");
				q.setParameter("userId", userId);
				// TODO Auto-generated method stub
				return q.list();
			}
		});
		Set<String> result = new HashSet<String>();
		result.addAll(temp);
		return result;
	}

	

}
