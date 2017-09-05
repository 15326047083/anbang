package com.anbang.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.anbang.dao.ICommonDao;

public class CommonDao<T> extends HibernateDaoSupport implements ICommonDao<T> {
	protected Class<T> entity;

	@SuppressWarnings("unchecked")
	public CommonDao() {
		super();
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		entity = (Class<T>) pt.getActualTypeArguments()[0];
	}

	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub

		getHibernateTemplate().delete(t);

	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	@Override
	public T get(String id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(entity, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> queryAll(final int pageNum, final int pageSize) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List>() {

			@Override
			public List doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				return arg0.createQuery("from " + entity.getName()).setFirstResult(pageNum).setMaxResults(pageSize)
						.list();

			}
		});
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		String hql = "select count(t.id) from " + entity.getName() + " as t";
		return ((Long) getHibernateTemplate().iterate(hql).next()).longValue();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryAll() {
		return getHibernateTemplate().find("from " + entity.getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryAllByParams(final String[] params, final Object[] values, final int pageNum,
			final int pageSize) {
		return this.createCriteriaByParams(params, values).setFirstResult(pageNum).setMaxResults(pageSize).list();

	}

	@Override
	public long countByParams(String[] params, Object[] values) {
		
		return Long.valueOf(this.createCriteriaByParams(params, values).setProjection(Projections.rowCount()).uniqueResult().toString());
	}
	
	
	public Criteria createCriteriaByParams(String[] params, Object[] values){
		Criteria c=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(entity);
		for (int i = 0; i < params.length; i++) {
			if (values[i] == null) {
				c.add(Restrictions.isNull(params[i]));
			} else {
				c.add(Restrictions.eq(params[i], values[i]));
			}
		}
		return c;
	}

	@Override
	public void saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
		
	}

}
