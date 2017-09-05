package com.anbang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.dao.IPointDao;
import com.anbang.po.Point;
import com.anbang.service.IPointService;

@Service("com.anbang.service.IPointService")
public class PointService extends CommonService<Point>implements IPointService {
	
	@Autowired
	public void setDao(IPointDao dao){
		this.dao=dao;
	}
}
