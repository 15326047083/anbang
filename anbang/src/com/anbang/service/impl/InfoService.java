package com.anbang.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.dao.IInfoDao;
import com.anbang.po.Info;
import com.anbang.service.IInfoService;

@Service("com.anbang.service.IInfoService")
public class InfoService extends CommonService<Info>implements IInfoService {
	
	@Autowired
	public void setDao(IInfoDao dao){
		this.dao=dao;
	}

	@Override
	public String saveInfo(Info info) {
		// TODO Auto-generated method stub
		return ((IInfoDao)dao).saveInfo(info);
	}

	
	@Override
	public List<Info> queryAllByEpidAndDeptId(int pageNum, int pageSize, String deptId, String epId) {
		// TODO Auto-generated method stub
		return  ((IInfoDao)dao).queryAllByEpAndDept(pageNum, pageSize, deptId, epId);
	}

	@Override
	public Long count(String deptId, String epId) {
		// TODO Auto-generated method stub
		return  ((IInfoDao)dao).count(deptId, epId);
	}

	@Override
	public List<Info> queryAllByCheckIdAndDate(String checkDeptId, Date start,
			Date end) {
		// TODO Auto-generated method stub
		return ((IInfoDao)dao).queryAllByCheckIdAndDate(checkDeptId, start, end);
	}
}