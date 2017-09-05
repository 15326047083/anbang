package com.anbang.po;

import java.io.Serializable;
import java.sql.Clob;
/**
 * 检查表，系统主要表格，采用一次检查对应多个item的方式
 * 其中unitid 为冗余 ，主要服务于按单元计分
 * @author HP2
 *
 */

public class Check implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8599368475745521062L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 检查信息id
	 */
	private String infoId;
	/**
	 * itemid
	 */
	private String itemId;
	/**
	 * 单元id
	 */
	private String unitId;
	/**
	 * 检查结果符合/不符合：
	 */
	private String result;
	/**
	 * 检查状态 合格/一般隐患/重大隐患
	 */
	private String status;
	/**
	 * 现场情况
	 */
	private String live;
	/**
	 * 分数
	 */
	private int score;
	/**
	 * 检查类型
	 */
	private String checkType;
	/**
	 * 重大隐患归0 0或1
	 */
	private int toZero;
	
	public Check(){}
	
	
	
	
	public Check(String id, String live) {
		super();
		this.id = id;
		this.live = live;
	}




	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	public int getToZero() {
		return toZero;
	}
	public void setToZero(int toZero) {
		this.toZero = toZero;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getLive() {
		return live;
	}
	public void setLive(String live) {
		this.live = live;
	}
	
	

	
	

}
