package com.anbang.service;

import java.util.List;

import com.anbang.po.Enterprise;
import com.anbang.vo.EpVo;

public interface IEnterpriseService extends ICommonService<Enterprise> {
	public List<Enterprise> getEnterpriseByDeptId(String deptId);
	public List<String> deleteAll(String [] ids);
	public EpVo getEpVoById(String id);
}
