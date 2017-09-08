package com.anbang.service;

import java.util.Date;
import java.util.List;

import com.anbang.po.Info;

public interface IInfoService extends ICommonService<Info> {
	public String saveInfo(Info info);

	public List<Info> queryAllByEpidAndDeptId(int pageNum, int pageSize, String deptId, String epId);

	public Long count(String deptId,String epId);
	
	/**
	 * 根据时间获取所有的check表值
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Info> queryAllByCheckIdAndDate(String checkDeptId,Date start,Date end);
}