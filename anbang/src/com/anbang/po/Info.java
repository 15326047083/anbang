package com.anbang.po;

import java.io.Serializable;
import java.util.Date;

/**
 	检查信息
 * @author lianj
 *
 */

public class Info implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = -365097518328942817L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 检查类型
	 */
	private String checkType;
	/**
	 * 检查机构
	 */
	private String checkDept;
	/**
	 * 检查机构id
	 */
	private String checkDeptId;
	/**
	 * 检查
	 */
	private String checkObject;
	/**
	 * 被检查企业
	 */
	private String checkEP;
	/**
	 * 被检查企业id
	 */
	private String checkEPId;
	/**
	 * 检查时间
	 */
	private Date checkDate;
	/**
	 * 检查人员姓名
	 */
	private String checkStaff;
	
	private int checkCommit;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getCheckDept() {
		return checkDept;
	}
	public void setCheckDept(String checkDept) {
		this.checkDept = checkDept;
	}
	public String getCheckDeptId() {
		return checkDeptId;
	}
	public void setCheckDeptId(String checkDeptId) {
		this.checkDeptId = checkDeptId;
	}
	public String getCheckObject() {
		return checkObject;
	}
	public void setCheckObject(String checkObject) {
		this.checkObject = checkObject;
	}
	public String getCheckEP() {
		return checkEP;
	}
	public void setCheckEP(String checkEP) {
		this.checkEP = checkEP;
	}
	public String getCheckEPId() {
		return checkEPId;
	}
	public void setCheckEPId(String checkEPId) {
		this.checkEPId = checkEPId;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckStaff() {
		return checkStaff;
	}
	public void setCheckStaff(String checkStaff) {
		this.checkStaff = checkStaff;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getCheckCommit() {
		return checkCommit;
	}
	public void setCheckCommit(int checkCommit) {
		this.checkCommit = checkCommit;
	}
	
	
	
	

}
