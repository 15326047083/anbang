package com.anbang.auth.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.auth.dao.IPrivilegeDao;
import com.anbang.auth.dao.IRolePrivilegeDao;
import com.anbang.auth.dao.IUserDao;
import com.anbang.auth.dao.IUserRoleDao;
import com.anbang.auth.po.User;
import com.anbang.auth.po.UserRole;
import com.anbang.auth.service.IUserService;
import com.anbang.auth.vo.AuthVo;
import com.anbang.service.impl.CommonService;

@Service("com.anbang.auth.service.IUserService")
public class UserService extends CommonService<User> implements IUserService {

	@Autowired
	public void setDao(IUserDao dao) {
		this.dao = dao;
	}

	@Autowired
	IUserRoleDao urDao;
	@Autowired
	IRolePrivilegeDao rpDao;

	@Override
	public AuthVo login(User u) {
		// TODO Auto-generated method stub
		u = ((IUserDao) dao).login(u);
		String userId = null;
		if (u != null)
			userId = u.getId();
		if (userId != null && userId.length() > 0) {
			AuthVo avo = new AuthVo();
			avo.setUserId(userId);
			avo.setUsername(u.getUsername());
			avo.setUserType(u.getUserType());
			Set<String> roles = urDao.getRoleByUserId(userId);
			avo.setRoles(roles);
			String[] prvis = new String[roles.size()];

			///avo.setPrivis(rpDao.getPrivilegeByRoleName(roles.toArray(prvis)));
			String deptId = u.getDeptId();
			if (deptId != null && deptId.length() > 0) {
				avo.setDeptId(deptId);
			} else {
				avo.setEpId(u.getEpId());
			}
			return avo;
		}
		return null;
	}

	@Override
	public void saveUser(User u) {
		u = ((IUserDao) dao).saveEpUser(u);
	
		if (urDao.getRoleByUserId(u.getId()).size()==0) {

			UserRole ur = new UserRole();
			ur.setUserId(u.getId());
			ur.setRoleId(u.getUserType());
			urDao.save(ur);
		}

	}

	@Override
	public User getUserByEpId(String epId) {
		// TODO Auto-generated method stub
		return ((IUserDao) dao).getUserByEpId(epId);
	}

}
