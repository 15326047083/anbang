package com.anbang.po;

import java.io.Serializable;

public class Point implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6331139329044326255L;
	private String id;
	private String pointDesc;
	private boolean pointType;
	private int pointValue;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPointDesc() {
		return pointDesc;
	}
	public void setPointDesc(String pointDesc) {
		this.pointDesc = pointDesc;
	}
	public boolean isPointType() {
		return pointType;
	}
	public void setPointType(boolean pointType) {
		this.pointType = pointType;
	}
	public int getPointValue() {
		return pointValue;
	}
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
	
	

}
