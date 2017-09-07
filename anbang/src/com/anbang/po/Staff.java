package com.anbang.po;

/**
 * 人员
 * @author Administrator
 *
 */
public class Staff {
	private String id;
	/**
	 * 人员姓名
	 */
	private String name;
	/**
	 * 人员描述
	 */
	private String staffDesc;
	/**
	 * 对应企业id
	 */
	private String epId;
	/**
	 * 对应机构id
	 */
	private String deptId;
	
	public Staff(){}
	
	
	public Staff(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStaffDesc() {
		return staffDesc;
	}
	public void setStaffDesc(String staffDesc) {
		this.staffDesc = staffDesc;
	}
	public String getEpId() {
		return epId;
	}
	public void setEpId(String epId) {
		this.epId = epId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	
	
}
