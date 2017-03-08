package com.simple.bsp.dictionnary.po;

public class PubDictionary {
	private String id;//主键
	private String num;//编码
	private String name;//名称
	private String fatherId;//父节点
	private Integer orderNum;//排序
	private String remarks;//备注
	private String enable;//是否启用
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	
}
