package com.anbang.po;

import java.io.Serializable;
/**
 * 企业项目表
 * @author lianj
 *
 */
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	/**
	 * 项目名称
	 */
	private String pName;
	/**
	 * 生产能力
	 */
	private String pCapacity;
	/**
	 * 存储能力
	 */
	private String loadCapacity;
	/**
	 * 危化品产品类型
	 */
	private String pType;
	
	/**
	 * 重点监管化学品类型
	 */
	private String pSur;
	
	
	/**
	 * 对应企业信息id
	 */
	private String dInfoId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	
	public String getpCapacity() {
		return pCapacity;
	}
	public void setpCapacity(String pCapacity) {
		this.pCapacity = pCapacity;
	}
	public String getLoadCapacity() {
		return loadCapacity;
	}
	public void setLoadCapacity(String loadCapacity) {
		this.loadCapacity = loadCapacity;
	}
	public String getpType() {
		return pType;
	}
	public void setpType(String pType) {
		this.pType = pType;
	}
	
	public String getdInfoId() {
		return dInfoId;
	}
	public void setdInfoId(String dInfoId) {
		this.dInfoId = dInfoId;
	}
	public String getpSur() {
		return pSur;
	}
	public void setpSur(String pSur) {
		this.pSur = pSur;
	}
	
	
	
	
	
}
