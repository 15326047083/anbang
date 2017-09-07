package com.anbang.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.anbang.dao.IUnitDao;
import com.anbang.po.Unit;

@Repository("com.anbang.dao.IUnitDao")
public class UnitDao extends CommonDao<Unit> implements IUnitDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Unit> getUnitKiByEpid(final String epid) {
		
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Unit>>() {

			@Override
			public List<Unit> doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query q = arg0.createQuery("select new Unit(id,score,ki) from Unit u where u.epid=(:epid)");
				q.setParameter("epid", epid);
				return q.list();
			}
		});
	}

	@Override
	public List<Unit> getUnitByEpid(final String epId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Unit>>() {

			@Override
			public List<Unit> doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				
				Query q = arg0.createQuery("from Unit u where u.epid=(:epid)");
				q.setParameter("epid", epId);
				return q.list();
			}
		});
	}

	@Override
	public void updateAllScore(String [] ids,double [] kis,int [] scores) {
		for(int i=0;i<ids.length;i++){
		getHibernateTemplate().bulkUpdate("update Unit u set u.ki=?,u.score=? where id=? ", new Object[]{kis[i],scores[i],ids[i]});
		}
	}

	
	

}
