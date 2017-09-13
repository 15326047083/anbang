package com.anbang.vo;

import com.anbang.tools.ExcelVOAttribute;

public class UnitVo {
	@ExcelVOAttribute(name = "id", column = "A")
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnitNum() {
		return unitNum;
	}
	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public double getKi() {
		return ki;
	}
	public void setKi(double ki) {
		this.ki = ki;
	}
	public String getEpid() {
		return epid;
	}
	public void setEpid(String epid) {
		this.epid = epid;
	}
	/**
	 * 单元编号
	 */
	@ExcelVOAttribute(name = "单元编号", column = "B")
	private String unitNum;
	/**
	 * 单元名称
	 */
	@ExcelVOAttribute(name = "单元名称", column = "C")
	private String unitName;
	
	/**
	 * 分数
	 */
	@ExcelVOAttribute(name = "分数", column = "D")
	private int score;
	
	/**
	 * 权重
	 */
	@ExcelVOAttribute(name = "权重", column = "E")
	private double ki;
	/**
	 * 对应企业
	 */
	@ExcelVOAttribute(name = "epid", column = "F")
	private String epid;
}
