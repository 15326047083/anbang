package com.anbang.vo;
/**
 * charts vo 转换为json
 * @author lianj
 *
 */
public class ChartsVo {
	/**
	 * 对应vo中的index，是检查日期的字符串形式
	 */
	private String index;
	/**
	 * 本次检查分数
	 */
	private int score;
	/**
	 * 饼图分布
	 */
	private int [] stat;
	/**
	 * 重大隐患信息
	 */
	private String [] exception;
	/**
	 * 一般隐患信息
	 */
	private String [] error;
	/**
	 * 被检查企业id
	 */
	private String check_id;
	
	private String infoId;
	
	
	
	public ChartsVo() {
		super();
	}
	public ChartsVo(String index, int score) {
		super();
		this.index = index;
		this.score = score;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public int[] getStat() {
		return stat;
	}
	public void setStat(int[] stat) {
		this.stat = stat;
	}
	public String[] getException() {
		return exception;
	}
	public void setException(String[] exception) {
		this.exception = exception;
	}
	public String[] getError() {
		return error;
	}
	public void setError(String[] error) {
		this.error = error;
	}
	public String getCheck_id() {
		return check_id;
	}
	public void setCheck_id(String check_id) {
		this.check_id = check_id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	
	
	
	
	
}
