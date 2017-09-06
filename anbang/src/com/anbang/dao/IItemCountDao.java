package com.anbang.dao;

import com.anbang.po.ItemCount;

public interface IItemCountDao extends ICommonDao<ItemCount>{
	/**
	 * 按企业获得item
	 * @param epid
	 * @return
	 */
	public Integer getItemCountByEpid(String epid);
}
