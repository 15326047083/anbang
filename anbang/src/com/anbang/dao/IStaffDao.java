package com.anbang.dao;

import java.util.List;

import com.anbang.po.Staff;

public interface IStaffDao extends ICommonDao<Staff>{
	public List<Staff> queryAllByDept(String deptId);
	public List<Staff> queryAllByEp(String epId);
}
