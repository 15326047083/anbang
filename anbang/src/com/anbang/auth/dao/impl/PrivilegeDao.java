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

import com.anbang.auth.dao.IPrivilegeDao;
import com.anbang.auth.po.Privilege;
import com.anbang.dao.impl.CommonDao;

@Repository("com.anbang.auth.dao.IPrivilegeDao")
public class PrivilegeDao extends CommonDao<Privilege> implements IPrivilegeDao{

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getPrivilegeByUserId(final String userId) {
		final Set<String> result=new HashSet<String>();
		// TODO Auto-generated method stub
		this.getHibernateTemplate().executeFind(new HibernateCallback<Set<String>>() {
			
			@Override
			public Set<String> doInHibernate(Session arg0) throws HibernateException, SQLException {
				Query q = arg0.createQuery("select privilegeDesc from Privilege p,RolePrivilege rp,UserRole ur where rp.prviId=p.id and ur.roleId=rp.roleId and ur.userId="+userId);
				List<String> temp = q.list();
				result.addAll(temp);
				return result;
			}
		});
		return result;
	}

}
