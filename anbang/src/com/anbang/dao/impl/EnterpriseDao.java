package com.anbang.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.anbang.dao.IEnterpriseDao;
import com.anbang.po.Enterprise;
import com.anbang.vo.EpVo;

@Repository("com.anbang.dao.IEnterpriseDao")
public class EnterpriseDao extends CommonDao<Enterprise> implements IEnterpriseDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Enterprise> getEnterpriceByDept(String deptId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByNamedParam("select new Enterprise(id,ename,areaId)from Enterprise e where e.deptId=(:deptId)", "deptId", deptId);
	}

	@Override
	public List<String> deleteAll(String[] ids) {
		this.getHibernateTemplate().deleteAll(this.getHibernateTemplate().findByNamedParam("from Enterprise e where e.id in (:ids)", "ids", ids));
		return Arrays.asList(ids);	
	}


	

}
