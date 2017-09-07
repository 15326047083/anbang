package com.anbang.dao;

import java.util.Date;
import java.util.List;

import com.anbang.po.Charts;

/**
 * 首页图形表格dao
 * @author HP2
 *
 */
public interface IChartsDao extends ICommonDao<Charts>{
	/**
	 * 根据企业主键获得检查图像点
	 * @param epid
	 * @return
	 */
	List<Charts> getDeptChartsByEpId(String epid, String chartType, Date start, Date end);
	

	

	
	public List<Charts> getSelfChatsByEpId(String epid);

	List<Date> getSafetyCheckDate(String epid, Date Start, Date end,String chartType);

	List<Charts> getDoubleChartsByEpId(String epid, String chartType, Date start, Date end);

	List<Date> getDoubleChartsDateByEpId(String epid, Date start, Date end);

	
}
