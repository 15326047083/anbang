package com.anbang.dao;

import java.util.List;

import com.anbang.po.Info;

public interface IInfoDao extends ICommonDao<Info>{
	public void commitCheck(Info info);
	public String saveInfo(Info info);

	List<Info> queryAllByEpAndDept(int pageNum,int pageSize,String deptId,String epId);
	
	public Long count(String deptId,String epId);
}
