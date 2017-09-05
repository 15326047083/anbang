package com.anbang.service;

import java.util.List;

import com.anbang.po.Info;

public interface IInfoService extends ICommonService<Info> {
	public String saveInfo(Info info);

	public List<Info> queryAllByEpidAndDeptId(int pageNum, int pageSize, String deptId, String epId);

	public Long count(String deptId,String epId);
}