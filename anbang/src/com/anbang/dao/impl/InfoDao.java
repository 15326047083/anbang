package com.anbang.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.anbang.dao.IInfoDao;
import com.anbang.po.Info;

@Repository("com.anbang.dao.IInfoDao")
public class InfoDao extends CommonDao<Info> implements IInfoDao {

	@Override
	public void commitCheck(Info i) {
		i.setCheckCommit(1);
		this.getHibernateTemplate().update(i);
		
	}

	@Override
	public String saveInfo(Info info) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(info);
		return info.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Info> queryAll(final int pageNum, final int pageSize) {
		// TODO Auto-generated method stub
		
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Info>>() {

			@Override
			public List<Info> doInHibernate(Session arg0) throws HibernateException, SQLException {
				Query q = arg0.createQuery("from Info i where i.checkDept is not null order by i.checkDate desc");
				q.setFirstResult(pageNum).setMaxResults(pageSize);
				return q.list();
			}
		});
		
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<Info> queryAllByEpAndDept(final int pageNum, final int pageSize, final String deptId, final String epId) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Info>>() {

			@Override
			public List<Info> doInHibernate(Session arg0) throws HibernateException, SQLException {
				String hql=null;
				Query q;
				if(deptId!=null){
					hql="from Info i where i.checkDeptId =(:deptId) and i.checkEPId=(:epId) order by i.checkDate desc";
					q = arg0.createQuery(hql);
					q.setParameter("deptId", deptId).setParameter("epId", epId);
				}
				else{
					hql="from Info i where i.checkDeptId is null and i.checkEPId=(:epId) order by i.checkDate desc";;
					q = arg0.createQuery(hql);
					q.setParameter("epId", epId);}
				q.setFirstResult(pageNum).setMaxResults(pageSize);
				return q.list();
			}
		});
	}

	@Override
	public Long count(String deptId, String epId) {
		// TODO Auto-generated method stub
		String hql = null;		
		if(deptId!=null)
				 hql= "select count(i.id) from Info i where i.checkDeptId = '"+deptId+"' and i.checkEPId= '"+epId+"'";
		else
			hql= "select count(i.id) from Info i where i.checkDeptId is null and i.checkEPId= '"+epId+"'";
		return ((Long)getHibernateTemplate().iterate(hql).next()).longValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Info> queryAllByCheckIdAndDate(final String checkDeptId, final Date start,final Date end) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Info>>(){

			@Override
			public List<Info> doInHibernate(Session arg0) throws HibernateException, SQLException {
				String hql="from Info i where i.checkDeptId=(:checkDeptId) and i.checkDate>=(:start) and i.checkDate<=(:end)";
				Query q;
				q = arg0.createQuery(hql);
				q.setParameter("checkDeptId", checkDeptId);
				q.setParameter("start", start);
				q.setParameter("end", end);
				// TODO Auto-generated method stub
				return q.list();
			}
			
		});
	}
	
	

	

}

