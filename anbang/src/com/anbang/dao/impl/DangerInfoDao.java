package com.anbang.dao.impl;

import org.springframework.stereotype.Repository;

import com.anbang.dao.IDangerInfoDao;
import com.anbang.po.DangerInfo;

@Repository(value="com.anbang.dao.IDangerInfoDao")
public class DangerInfoDao extends CommonDao<DangerInfo> implements IDangerInfoDao {

	
}
