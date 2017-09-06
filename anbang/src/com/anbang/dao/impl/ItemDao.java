package com.anbang.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.anbang.dao.IItemDao;
import com.anbang.po.Item;

@Repository(value="com.anbang.dao.IItemDao")
public class ItemDao extends CommonDao<Item> implements IItemDao {

	@Override
	public String formatItemNumber(String itemNum) {
		String [] temp = itemNum.trim().split("\\.");
		
		return "第"+temp[0]+"单元第"+temp[1]+"条";
	}

	/**
	 * 有问题，这个方法应该放在service中，在第二版中必须更正
	 */
	@Override
	public String[] getItemVoFormat(final List<String> itemIds) {
		String [] result;
		@SuppressWarnings("unchecked")
		List<String> temp=getHibernateTemplate().executeFind(new HibernateCallback<List<String>>() {

			@SuppressWarnings("unchecked")
			@Override
			public List<String> doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query q = arg0.createQuery("select itemNum from Item i where i.id in (:args)");
				q.setParameterList("args", itemIds);
				
				return q.list();
			}
		});
		
		result=new String[temp.size()];
		for(int i=0;i<result.length;i++){
			result[i]=formatItemNumber(temp.get(i));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> queryAllByUnitId(final String unidId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Item>>() {

			@Override
			public List<Item> doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query q = arg0.createQuery("select new Item(id,itemNum,itemScore,expire) from Item i where i.unitId=(:unitId)");
				q.setParameter("unitId", unidId);
				return q.list();
			}
		});
	
	}

	@Override
	public long getItemCount(final List<String> unitId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<Long>() {

			@Override
			public Long doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query q = arg0.createQuery("select count(*) from Item item where item.unitId in (:unitId)");
				q.setParameterList("unitId", unitId);
				return (Long) q.uniqueResult();
			}
		});
	}

	@Override
	public List<Item> queryNoExpireByUnitId(final String unitId) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Item>>() {

			@Override
			public List<Item> doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query q = arg0.createQuery("select new Item(id,itemNum,itemScore,expire) from Item i where i.unitId=(:unitId) and  i.expire=0");
				q.setParameter("unitId", unitId);
				return q.list();
			}
		});
	}

	

}
