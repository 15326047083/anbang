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

import com.anbang.auth.dao.IRolePrivilegeDao;
import com.anbang.auth.po.RolePrivilege;
import com.anbang.dao.impl.CommonDao;
@Repository("com.anbang.auth.dao.IRolePrivilegeDao")
public class RolePrivilegeDao extends CommonDao<RolePrivilege> implements IRolePrivilegeDao{

	@Override
	public Set<String> getPrivilegeByRoleName(final String ... roleName) {
		@SuppressWarnings("unchecked")
		List<String> temp=getHibernateTemplate().executeFind(new HibernateCallback<List>() {

			@Override
			public List doInHibernate(Session arg0) throws HibernateException, SQLException {
				Query q = arg0.createQuery("select rp.prviId from RolePrivilege rp where rp.roleId in(:roleName)");
				q.setParameterList("roleName", roleName);
				// TODO Auto-generated method stub
				return q.list();
			}
		});
		Set<String> result = new HashSet<String>();
		result.addAll(temp);
		return result;
	}
	
}
