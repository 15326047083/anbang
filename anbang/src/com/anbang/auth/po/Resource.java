package com.anbang.auth.po;

import java.io.Serializable;

/**
 * 权限模块 资源类
 * @author lianj
 *
 */
public class Resource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String resourceName;
	private String resourceUrl;
	private String priviId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public String getPriviId() {
		return priviId;
	}
	public void setPriviId(String priviId) {
		this.priviId = priviId;
	}
	
	
	
	
}
