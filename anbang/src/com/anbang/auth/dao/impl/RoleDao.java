package com.anbang.auth.dao.impl;

import org.springframework.stereotype.Repository;

import com.anbang.auth.dao.IRoleDao;
import com.anbang.auth.po.Role;
import com.anbang.dao.impl.CommonDao;

@Repository("com.anbang.auth.dao.IRoleDao")
public class RoleDao extends CommonDao<Role> implements IRoleDao{

}
