package com.anbang.auth.po;

import java.io.Serializable;
/**
 * È¨ÏÞÄ£¿é  
 * @author lianj
 *
 */
public class Privilege implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String privilegeName;
	private String privilegeDesc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrivilegeName() {
		return privilegeName;
	}
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	public String getPrivilegeDesc() {
		return privilegeDesc;
	}
	public void setPrivilegeDesc(String privilegeDesc) {
		this.privilegeDesc = privilegeDesc;
	}
	
}
