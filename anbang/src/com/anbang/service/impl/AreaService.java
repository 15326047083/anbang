package com.anbang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.dao.IAreaDao;
import com.anbang.dao.IEnterpriseDao;
import com.anbang.po.Area;
import com.anbang.po.Enterprise;
import com.anbang.service.IAreaService;
import com.anbang.service.IEnterpriseService;

@Service("com.anbang.service.IAreaService")
public class AreaService extends CommonService<Area>implements IAreaService {
	
	@Autowired
	public void setDao(IAreaDao dao){
		this.dao=dao;
	}
}
