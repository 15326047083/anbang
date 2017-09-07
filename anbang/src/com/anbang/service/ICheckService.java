package com.anbang.service;

import java.util.List;

import com.anbang.po.Check;
import com.anbang.vo.CheckVo;

/**
 * 检查
 * @author Administrator
 *
 */
public interface ICheckService extends ICommonService<Check> {
	public List<CheckVo> queryCheckByInfoId(String infoId);
	public Integer getTotalScoreByInfoId(String infoId);
	public String getStatByInfoId(String infoId,String epId);
	public String getExIds(String infoId);
	public String getErrorIds(String infoId);
	public String getCheckByItemNum(String itemNum,String infoId);
	public CheckVo queryCheckByCheckId(String checkId);
	public List<CheckVo> queryCheckByCheckIds(String [] ids);
	public Long countCheckByEpid(String epId);
	public Long countCheckByEpid2(String epId);
	
}
