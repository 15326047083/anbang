package com.anbang.auth.po;

import java.io.Serializable;

public class RolePrivilege implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String roleId;
	private String prviId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getPrviId() {
		return prviId;
	}
	public void setPrviId(String prviId) {
		this.prviId = prviId;
	}
	
	
}
