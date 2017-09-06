package com.anbang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.dao.IDangerInfoDao;
import com.anbang.po.DangerInfo;
import com.anbang.service.IDangerInfoService;

@Service("com.anbang.service.IDangerInfoService")
public class DangerInfoService extends CommonService<DangerInfo>implements IDangerInfoService {
	
	@Autowired
	public void setDao(IDangerInfoDao dao){
		this.dao=dao;
	}
}
