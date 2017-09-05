package com.anbang.auth.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.auth.dao.IPrivilegeDao;
import com.anbang.auth.po.Privilege;
import com.anbang.auth.service.IPrivilegeService;

import com.anbang.service.impl.CommonService;

@Service("com.anbang.auth.service.IPrivilegeService")
public class PrivilegeService extends CommonService<Privilege> implements IPrivilegeService{

	@Autowired
	public void setDao(IPrivilegeDao dao){
		this.dao=dao;
	}
	
	@Override
	public Set<String> getPrivilegeByUserId(String userId) {
		// TODO Auto-generated method stub
		return ((IPrivilegeDao)dao).getPrivilegeByUserId(userId);
	}

}
