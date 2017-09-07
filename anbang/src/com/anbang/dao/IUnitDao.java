package com.anbang.dao;

import java.util.List;

import com.anbang.po.Unit;


public interface IUnitDao extends ICommonDao<Unit> {
	/**
	 * 取得某一企业检测单元的权重与分数，按单元id分组
	 * @param epid
	 * @return
	 */
	public List<Unit> getUnitKiByEpid(String epid);
	
	public List<Unit> getUnitByEpid(String epId);
	
	public void updateAllScore(String [] ids,double [] kis,int [] scores);

	
}
