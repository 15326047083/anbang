package com.anbang.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.anbang.dao.ICheckDao;
import com.anbang.po.Check;
import com.anbang.vo.UnitScoreVo;

@Repository("com.anbang.dao.ICheckDao")
public class CheckDao extends CommonDao<Check> implements ICheckDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UnitScoreVo> getScoreByInfoGByUnit(final String infoId) {
		return getHibernateTemplate().executeFind(new  HibernateCallback<List<UnitScoreVo>>() {

			@Override
			public List<UnitScoreVo> doInHibernate(Session arg0) throws HibernateException,
					SQLException {
					//按unitid统计总分和最小的toZero值
					Query q = arg0.createQuery("select unitId,sum(score),min(toZero) from Check where infoId=(:infoId) group by unitId");
					q.setParameter("infoId", infoId);
					List<Object []> temp = q.list();
					List<UnitScoreVo> result = new ArrayList<UnitScoreVo>();
					UnitScoreVo usv;
					for(Object [] t:temp){
						usv = new UnitScoreVo(t[0].toString(),Integer.parseInt(t[1].toString()),Integer.parseInt(t[2].toString()));
						result.add(usv);
					}
				
				return result;
			}
			
			
		});
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getStatByInfoGByStatus(final String infoId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Integer>>() {

			@Override
			public List<Integer> doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query q = arg0.createQuery("select status,count(status) from Check c where c.infoId = (:infoId) group by status");
				q.setParameter("infoId", infoId);
				
				return q.list();
			}
			
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getExIds(final String infoId) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Integer>>() {

			@Override
			public List<Integer> doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query q = arg0.createQuery("select id from Check c where c.infoId = (:infoId) and c.status= (:status)");
				q.setParameter("infoId", infoId).setParameter("status", "一般隐患");
				return q.list();
			}
			
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getErrorIds(final String infoId) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Object>>() {

			@Override
			public List<Object> doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query q = arg0.createQuery("select id from Check c where c.infoId = (:infoId) and c.status= (:status)");
				q.setParameter("infoId", infoId).setParameter("status", "重大隐患");
				return q.list();
			}
			
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> queryAllByInfoId(final String infoId) {
			
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Object[]>>() {

		
			public java.util.List<Object[]> doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query q = arg0.createQuery("select it.itemNum,it.itemContent,it.itemLaw,c.live,c.status,c.result,c.checkType  from Check c,Item it where it.id=c.itemId and c.infoId=(:infoId)");
				q.setParameter("infoId", infoId);
				return q.list();
			}
		});
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getNoneIds(final String infoId) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Object>>() {

			@Override
			public List<Object> doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query q = arg0.createQuery("select c.itemId from Check c,Item it where c.infoId = (:infoId) and c.status= (:status) and it.toNone=1 and c.itemId=it.id");
				q.setParameter("infoId", infoId).setParameter("status", "重大隐患");
				return q.list();
			}
			
		});
	}

	@Override
	public String getCheckByItemNum(final String itemNum,final String infoId) {
		return getHibernateTemplate().execute(new HibernateCallback<String>() {

			@Override
			public String doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query q = arg0.createQuery("select c.id from Check c where c.itemId=(:itemNum) and c.infoId=(:infoId)");
				q.setParameter("itemNum", itemNum).setParameter("infoId", infoId);
				
				return (String) (q.list().size()==0?null:q.list().get(0));
			}
		});

	}

	@Override
	public Check getExCheck(final String id) {
		return getHibernateTemplate().execute(new HibernateCallback<Check>() {

			@Override
			public Check doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query q = arg0.createQuery("select new Check(id,live) from Check c where c.id = (:id)");
				q.setParameter("id", id);
				return (Check) q.uniqueResult();
			}
			
		});
	}

	@Override
	public Object[] getCheckVo(final String id) {
		return getHibernateTemplate().execute(new HibernateCallback<Object[]>() {

			
			public Object[] doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query q = arg0.createQuery("select it.itemNum,it.itemContent,it.itemLaw,c.live,c.status,c.result  from Check c,Item it where it.id=c.itemId and c.id=(:id)");
				q.setParameter("id", id);
				return (Object[]) q.uniqueResult();
			}
		});
	}

	

}
