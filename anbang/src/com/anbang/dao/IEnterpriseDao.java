package com.anbang.dao;

import java.util.List;

import com.anbang.po.Enterprise;
import com.anbang.vo.EpVo;

public interface IEnterpriseDao extends ICommonDao<Enterprise>{
	public List<Enterprise> getEnterpriceByDept(String deptId);
	public List<String> deleteAll(String [] ids); 
	
}
