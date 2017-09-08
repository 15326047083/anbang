package com.anbang.po;

import java.io.Serializable;

public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1422223383023119873L;
	private String id;
	/**
	 * 检查类型
	 */
	private String itemType;
	/**
	 * 检查内容
	 */
	private String itemContent;
	/**
	 * 检查依据
	 */
	private String itemLaw;
	/**
	 * 编号
	 */
	private String itemNum;
	private String unitId;
	/**
	 * 分数
	 */
	private int itemScore;
	/**
	 * 是否过期，过期1，否则为0
	 * 状态
	 */
	private int expire;
	/**
	 * 全局否决项，是全局否决项为1，否则为0
	 * 是否否决
	 */
	private int toNone;

	
	public Item(){}
	
	public Item(String id, String itemNum,int itemScore,int expire) {
		super();
		this.id = id;
		this.itemNum = itemNum;
		this.itemScore=itemScore;
		this.expire=expire;
		
	}
	
	
	public Item(String id, String itemNum, int itemScore,int expire, int toNone, String itemContent, String itemType, String itemLaw,
			 String unitId) {
		super();
		this.id = id;
		this.itemType = itemType;
		this.itemContent = itemContent;
		this.itemLaw = itemLaw;
		this.itemNum = itemNum;
		this.unitId = unitId;
		this.itemScore = itemScore;
		this.expire = expire;
		this.toNone = toNone;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemContent() {
		return itemContent;
	}
	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}
	public String getItemLaw() {
		return itemLaw;
	}
	public void setItemLaw(String itemLaw) {
		this.itemLaw = itemLaw;
	}

	
	public String getItemNum() {
		return itemNum;
	}
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public int getItemScore() {
		return itemScore;
	}
	public void setItemScore(int itemScore) {
		this.itemScore = itemScore;
	}

	public int getToNone() {
		return toNone;
	}

	public void setToNone(int toNone) {
		this.toNone = toNone;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}


	
	

	
	
}
