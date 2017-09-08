package com.anbang.dao;

import java.util.Date;
import java.util.List;

import com.anbang.po.Check;
import com.anbang.vo.UnitScoreVo;


public interface ICheckDao extends ICommonDao<Check>{
	/**
	 * 根据info选取本次检测所有分数的和，按unit分组
	 * @param infoId
	 * @return 包含unitid 和分组后分数
	 */
	public List<UnitScoreVo> getScoreByInfoGByUnit(String infoId);
	/**
	 * 根据info获取本次检查各项分布数据
	 * @param infoId
	 * @return
	 */
	public List<Object[]> getStatByInfoGByStatus(String infoId);
	/**
	 * 根据infoid返回一般隐患ids
	 * @param infoId
	 * @return
	 */
	public List<Object> getExIds(String infoId);
	
	/**
	 * 根据infoid返回重大隐患ids
	 * @param infoId
	 * @return
	 */
	public List<Object> getErrorIds(String infoId);
	
	
	public List<Object[]> queryAllByInfoId(String infoId);
	
	/**
	 * 根据infoID以及隐患情况返回可以否决的itemID
	 * @param infoId
	 * @return
	 */
	public List<Object> getNoneIds(String infoId);
	
	/**
	 * 根据ItemNumber拿到CheckId
	 * @param itemNum
	 * @return
	 */
	public String getCheckByItemNum(String itemNum,String infoId);
	/**
	 * 根据id获得一般隐患或重大隐患的check信息（id和现场情况）
	 * @param id
	 * @return
	 */
	public Check getExCheck(String id);
	
	/**
	 * 根据id获得checkVo
	 * @param id
	 * @return
	 */
	public Object[] getCheckVo(String id);
	/**
	 * 通过一段时间得到所有check的信息
	 * @param infoId
	 * @param start
	 * @param end
	 * @return
	 */

	List<Check> queryAllByDeptIdAndDate(List<String> infoIds);
	
	public List<String> getIdByDate(Date start,Date end);

}
