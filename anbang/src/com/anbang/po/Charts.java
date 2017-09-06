package com.anbang.po;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

/**
 * 首页charts数据
 * @author HP2
 *
 */
public class Charts implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 分数
	 */
	private int score;
	/**
	 * 检查时间
	 */
	private Date date;
	/**
	 * 饼图数据 格式为xx:xx:xx
	 */
	private String pieData;
	/**
	 * 简介窗口中一般隐患数据 格式为检查表中对应的itemid，以分号分割 xx:xx:xx
	 */
	private Clob expData;
	/**
	 * 简介窗口中重大隐患数据 格式为检查表中对应的itemid，以分号分割 xx:xx:xx
	 */
	private Clob errorData;
	/**
	 * 对应的企业id
	 */
	private String epid;
	/**
	 * 对应的检查信息id，主要用于图上点击后通过infoId调出对应的所有check生成表格
	 */
	private String infoId;
	/**
	 * 检查类型（自查/检查）
	 */
	private String chartType;
	
	
	public Charts() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPieData() {
		return pieData;
	}
	public void setPieData(String pieData) {
		this.pieData = pieData;
	}
	
	
	public Clob getExpData() {
		return expData;
	}
	public void setExpData(Clob expData) {
		this.expData = expData;
	}
	public Clob getErrorData() {
		return errorData;
	}
	public void setErrorData(Clob errorData) {
		this.errorData = errorData;
	}
	
	public String getEpid() {
		return epid;
	}
	public void setEpid(String epid) {
		this.epid = epid;
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public Charts(int score, Date date) {
		super();
		this.score = score;
		this.date = date;
	}
	
	
	
	
}
