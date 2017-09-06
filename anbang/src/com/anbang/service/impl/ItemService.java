package com.anbang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.dao.IItemDao;
import com.anbang.po.Item;
import com.anbang.service.IItemService;

@Service("com.anbang.service.IItemService")
public class ItemService extends CommonService<Item>implements IItemService {
	
	@Autowired
	public void setDao(IItemDao dao){
		this.dao=dao;
	}

	@Override
	public List<Item> queryAllByUnitId(String unitId) {
		// TODO Auto-generated method stub
		return ((IItemDao)dao).queryAllByUnitId(unitId);
	}

	@Override
	public List<Item> queryAllNoExpireUnitId(String unitId) {
		// TODO Auto-generated method stub
		return ((IItemDao)dao).queryNoExpireByUnitId(unitId);
		}
}
