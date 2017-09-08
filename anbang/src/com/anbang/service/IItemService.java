package com.anbang.service;

import java.util.List;

import com.anbang.po.Item;

public interface IItemService extends ICommonService<Item> {
	public List<Item> queryAllByUnitId(String unitId);
	public List<Item> queryAllNoExpireUnitId(String unitId);
	List<Item> queryAllItemByUnitId(String unitId);
}
