package com.anbang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.dao.IUnitDao;
import com.anbang.po.Unit;
import com.anbang.service.IUnitService;

@Service("com.anbang.service.IUnitService")
public class UnitService extends CommonService<Unit>implements IUnitService {
	
	@Autowired
	public void setDao(IUnitDao dao){
		this.dao=dao;
	}

	@Override
	public List<Unit> getAllUnitByEpId(String epId) {
		// TODO Auto-generated method stub
		return ((IUnitDao)dao).getUnitByEpid(epId);
	}

	@Override
	public void updateScore(String[] ids, double[] kis,int [] scores) {
		// TODO Auto-generated method stub
		((IUnitDao)dao).updateAllScore(ids, kis,scores);
		
	}

	@Override
	public void saveOrUpdate(List<Unit> importList) {
		// TODO Auto-generated method stub
		((IUnitDao)dao).saveOrUpdate(importList);
	}
	
	
}
