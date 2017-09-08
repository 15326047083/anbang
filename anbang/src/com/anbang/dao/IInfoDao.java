package com.anbang.dao;

import java.util.Date;
import java.util.List;

import com.anbang.po.Info;

public interface IInfoDao extends ICommonDao<Info>{
	public void commitCheck(Info info);
	public String saveInfo(Info info);

	List<Info> queryAllByEpAndDept(int pageNum,int pageSize,String deptId,String epId);
	
	public Long count(String deptId,String epId);
	/**
	 * 根据开始时间和结束时间和id得到所有info的值
	 * @param checkDeptId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Info> queryAllByCheckIdAndDate(String checkDeptId,Date start,Date end);
}
