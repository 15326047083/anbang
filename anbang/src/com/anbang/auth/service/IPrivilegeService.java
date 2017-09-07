package com.anbang.auth.service;

import java.util.Set;

import com.anbang.auth.po.Privilege;
import com.anbang.service.ICommonService;

public interface IPrivilegeService extends ICommonService<Privilege>{
	public Set<String> getPrivilegeByUserId(String userId);

}
