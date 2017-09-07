package com.anbang.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.anbang.dao.IItemCountDao;
import com.anbang.po.ItemCount;

@Repository("com.anbang.dao.IItemCountDao")
public class ItemCountDao extends CommonDao<ItemCount> implements IItemCountDao{

	@Override
	public Integer getItemCountByEpid(final String epid) {
		
		return getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session arg0) throws HibernateException, SQLException {
				Query q = arg0.createQuery("select itemCount from ItemCount ic where ic.epid=(:args)");
				q.setParameter("args", epid);
				return (Integer) q.uniqueResult();
			}
		});
	}

}
