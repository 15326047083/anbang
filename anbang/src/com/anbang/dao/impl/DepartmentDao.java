package com.anbang.dao.impl;

import org.springframework.stereotype.Repository;

import com.anbang.dao.IDepartmentDao;

import com.anbang.po.Department;

@Repository(value="com.anbang.dao.IDepartmentDao")
public class DepartmentDao extends CommonDao<Department> implements IDepartmentDao{

}
