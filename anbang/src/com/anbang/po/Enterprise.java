package com.anbang.po;

public class Enterprise {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 企业名称
	 */
	private String ename;
	/**
	 * 统一信用代码
	 */
	private String ecode;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 企业性质
	 */
	private String etype;
	/**
	 * 法人代表姓名
	 */
	private String corporation;
	/**
	 * 电话号码
	 */
	private String tel;
	/**
	 * 传真号码
	 */
	private String fax;
	/**
	 * 电邮
	 */
	private String email;
	/**
	 * 生产许可
	 */
	private String permision;
	/**
	 * 企业人数
	 */
	private String count;
	/**
	 * 企业简介
	 */
	private String desc;
	/**
	 * 企业类别
	 */
	private String especial;
	/**
	 * 属地监管部门id
	 */
	private String deptId;
	/**
	 * 所属区域id
	 */
	private String areaId;
	
	/**
	 * 主要负责人数量
	 */
	private int leaderCount;
	
	/**
	 * 中层人员数量
	 */
	private int middleCount;
	/**
	 * 安全人员数量
	 */
	private int safetyCount;
	/**
	 * 技术人员
	 */
	private int techCount;
	/**
	 * 工作人员
	 */
	private int worker;
	/**
	 *现场操作人员
	 */
	private int liveWorker;
	
	public Enterprise(){}
	
	/**
	 * 自定义构造方法，只初始化id和ename
	 * @param id
	 * @param ename
	 */
	public Enterprise(String id,String ename,String areaId){
		this.id=id;
		this.ename=ename;
		this.areaId=areaId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEtype() {
		return etype;
	}
	public void setEtype(String etype) {
		this.etype = etype;
	}
	public String getCorporation() {
		return corporation;
	}
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPermision() {
		return permision;
	}
	public void setPermision(String permision) {
		this.permision = permision;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getEspecial() {
		return especial;
	}
	public void setEspecial(String especial) {
		this.especial = especial;
	}
	
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public int getLeaderCount() {
		return leaderCount;
	}
	public void setLeaderCount(int leaderCount) {
		this.leaderCount = leaderCount;
	}
	public int getMiddleCount() {
		return middleCount;
	}
	public void setMiddleCount(int middleCount) {
		this.middleCount = middleCount;
	}
	public int getSafetyCount() {
		return safetyCount;
	}
	public void setSafetyCount(int safetyCount) {
		this.safetyCount = safetyCount;
	}
	public int getTechCount() {
		return techCount;
	}
	public void setTechCount(int techCount) {
		this.techCount = techCount;
	}
	public int getWorker() {
		return worker;
	}
	public void setWorker(int worker) {
		this.worker = worker;
	}
	public int getLiveWorker() {
		return liveWorker;
	}
	public void setLiveWorker(int liveWorker) {
		this.liveWorker = liveWorker;
	}
	
	
	
	
}
