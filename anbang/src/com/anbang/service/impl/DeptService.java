package com.anbang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.dao.IDepartmentDao;

import com.anbang.po.Department;
import com.anbang.service.IDeptService;

@Service("com.anbang.service.IDeptService")
public class DeptService extends CommonService<Department> implements IDeptService {

	@Autowired
	public void setDao(IDepartmentDao dao){
		this.dao=dao;
	}
}
