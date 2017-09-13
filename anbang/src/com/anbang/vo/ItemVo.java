package com.anbang.vo;

import com.anbang.tools.ExcelVOAttribute;

public class ItemVo {
	@ExcelVOAttribute(name = "id", column = "A")
	private String id;
	/**
	 * 检查类型
	 */
	@ExcelVOAttribute(name = "检查类型", column = "G")
	private String itemType;
	/**
	 * 检查内容
	 */
	@ExcelVOAttribute(name = "检查内容", column = "F")
	private String itemContent;
	/**
	 * 检查依据
	 */
	@ExcelVOAttribute(name = "检查依据", column = "H")
	private String itemLaw;
	/**
	 * 编号
	 */
	@ExcelVOAttribute(name = "编号", column = "B")
	private String itemNum;
	@ExcelVOAttribute(name = "unitId", column = "I")
	private String unitId;
	/**
	 * 分数
	 */
	@ExcelVOAttribute(name = "分数", column = "C")
	private int itemScore;
	/**
	 * 是否过期，过期1，否则为0
	 * 状态
	 */
	@ExcelVOAttribute(name = "状态", column = "D")
	private int expire;
	/**
	 * 全局否决项，是全局否决项为1，否则为0
	 * 是否否决
	 */
	@ExcelVOAttribute(name = "是否否决", column = "E")
	private int toNone;
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
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}
	public int getToNone() {
		return toNone;
	}
	public void setToNone(int toNone) {
		this.toNone = toNone;
	}

}
