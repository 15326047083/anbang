package com.anbang.po;

import java.io.Serializable;
/**
 * 企业危险 项目表
 * @author lianj
 *
 */

public class DangerInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	/**
	 * 对应企业 一对一
	 */
	private String epId;
	/**
	 * 技术
	 */
	private String tech;
	/**
	 * 产品
	 */
	private String source;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEpId() {
		return epId;
	}
	public void setEpId(String epId) {
		this.epId = epId;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	
	
}
