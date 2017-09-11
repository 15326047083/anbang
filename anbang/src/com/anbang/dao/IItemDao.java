package com.anbang.dao;

import java.util.List;

import com.anbang.po.Item;

public interface IItemDao extends ICommonDao<Item>{
	/**
	 * 将*.*类型的变成“第*单元第*条”
	 * @param itemNum
	 * @return
	 */
	public String formatItemNumber(String itemNum);
	/**
	 * 根据ids返回一组符合format的数据的String数组
	 * @param itemIds
	 * @return
	 */
	
	public String[] getItemVoFormat(List<String> itemIds);
	
	public List<Item> queryAllByUnitId(String unidId);
	public List<Item> queryNoExpireByUnitId(String unitId);
	public long getItemCount(List<String> unitId);
	List<Item> queryAllItemByUnitId(String unitId);
	/**
	 * 保存或修改恢复item表
	 * @param importList
	 * @param unitId
	 */
	public void saveOrUpdate(List<Item> importList,String unitId);
	

	
}
