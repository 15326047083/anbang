package com.anbang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.dao.IStaffDao;
import com.anbang.po.Staff;
import com.anbang.service.IStaffService;

@Service("com.anbang.service.IStaffService")
public class StaffService extends CommonService<Staff> implements IStaffService{
	@Autowired
	public void setDao(IStaffDao dao){
		this.dao=dao;
	}

	@Override
	public List<Staff> queryAllByDept(String deptId) {
		// TODO Auto-generated method stub
		return ((IStaffDao)dao).queryAllByDept(deptId);
	}
	@Override
	public List<Staff> queryAllByEp(String epId) {
		// TODO Auto-generated method stub
		return ((IStaffDao)dao).queryAllByEp(epId);
	}
}
