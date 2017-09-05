package com.anbang.vo;

/**
 * 按unit统计分数
 * @author HP2
 *
 */
public class UnitScoreVo {
	private String unitId;
	private int score;
	private int toZero;
	
	
	public UnitScoreVo(String unitId, int score,int toZero) {
		super();
		this.unitId = unitId;
		this.score = score;
		this.setToZero(toZero);
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getToZero() {
		return toZero;
	}
	public void setToZero(int toZero) {
		this.toZero = toZero;
	}
	
	
}
