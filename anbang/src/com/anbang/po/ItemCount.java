package com.anbang.po;

import java.io.Serializable;

/**
 * Item辅助表格，主要用于统计企业检查item总个数
 * @author Administrator
 *
 */
public class ItemCount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 检查项目计数
	 */
	private int itemCount;
	/**
	 * 对应的企业
	 */
	private String epid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public String getEpid() {
		return epid;
	}
	public void setEpid(String epid) {
		this.epid = epid;
	}
	
	
}
