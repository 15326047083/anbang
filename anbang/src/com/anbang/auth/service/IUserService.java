package com.anbang.auth.service;

import com.anbang.auth.po.User;
import com.anbang.auth.vo.AuthVo;
import com.anbang.service.ICommonService;

public interface IUserService extends ICommonService<User>{
	public AuthVo login(User u);
	public void saveUser(User u);
	public User getUserByEpId(String epId);
	
}
