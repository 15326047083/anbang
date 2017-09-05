package com.anbang.service;

import java.util.List;

import com.anbang.po.Staff;

public interface IStaffService extends ICommonService<Staff>{
	public List<Staff> queryAllByDept(String deptId);
	public List<Staff> queryAllByEp(String epId);
}
