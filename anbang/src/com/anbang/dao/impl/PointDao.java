package com.anbang.dao.impl;

import org.springframework.stereotype.Repository;

import com.anbang.dao.IPointDao;
import com.anbang.po.Point;

@Repository("com.anbang.dao.IPointDao")
public class PointDao extends CommonDao<Point> implements IPointDao {

	

}
