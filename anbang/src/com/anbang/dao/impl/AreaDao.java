package com.anbang.dao.impl;

import org.springframework.stereotype.Repository;

import com.anbang.dao.IAreaDao;
import com.anbang.dao.IPointDao;
import com.anbang.po.Area;
import com.anbang.po.Point;

@Repository("com.anbang.dao.IAreaDao")
public class AreaDao extends CommonDao<Area> implements IAreaDao {

	

}
