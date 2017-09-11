package com.anbang.service;

import java.util.List;

import com.anbang.po.Unit;

public interface IUnitService extends ICommonService<Unit> {
	public List<Unit> getAllUnitByEpId(String epId);
	public void updateScore(String[] ids,double [] kis,int [] scores);
	
	public void saveOrUpdate(List<Unit> importList);
}
