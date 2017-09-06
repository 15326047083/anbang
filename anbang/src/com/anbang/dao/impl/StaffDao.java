package com.anbang.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.anbang.dao.IStaffDao;
import com.anbang.po.Staff;

@Repository("com.anbang.dao.IStaffDao")
public class StaffDao extends CommonDao<Staff> implements IStaffDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Staff> queryAllByDept(final String deptId) {
		
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Staff>>() {
			@Override
			public List<Staff> doInHibernate(Session arg0) throws HibernateException, SQLException {
				Query q = arg0.createQuery("select new Staff(id,name) from Staff s where s.deptId=(:deptId)");
				q.setParameter("deptId",deptId);
				return q.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Staff> queryAllByEp(final String epId) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Staff>>() {
			@Override
			public List<Staff> doInHibernate(Session arg0) throws HibernateException, SQLException {
				Query q = arg0.createQuery("select new Staff(id,name) from Staff s where s.epId=(:epId)");
				q.setParameter("epId",epId);
				return q.list();
			}
		});
	}

}
