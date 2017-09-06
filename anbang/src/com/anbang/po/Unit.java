package com.anbang.po;

import java.io.Serializable;
/**
 * 检测单元
 * @author Administrator
 *
 */
public class Unit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4125116093610962615L;
	private String id;
	/**
	 * 单元编号
	 */
	private String unitNum;
	/**
	 * 单元名称
	 */
	private String unitName;
	
	/**
	 * 分数
	 */
	private int score;
	
	/**
	 * 权重
	 */
	private double ki;
	/**
	 * 对应企业
	 */
	private String epid;
	
	public Unit(){}
	
	public Unit(String id, int score, double ki) {
		super();
		this.id = id;
		this.score = score;
		this.ki = ki;
	}
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
	
	
	

}
