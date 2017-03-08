package com.simple.bsp.cant.po;

//全国各个行政区码表
public class PubCant {
	private String cantCode;// 主键
	private String cantName;// 行政区全称
	private String shortName;// 行政区简称
	private String cantType;// 行政区类别:1省级2市级3区(县)
	private String superCode;// 父节点

	public String getCantCode() {
		return cantCode;
	}

	public void setCantCode(String cantCode) {
		this.cantCode = cantCode;
	}

	public String getCantName() {
		return cantName;
	}

	public void setCantName(String cantName) {
		this.cantName = cantName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCantType() {
		return cantType;
	}

	public void setCantType(String cantType) {
		this.cantType = cantType;
	}

	public String getSuperCode() {
		return superCode;
	}

	public void setSuperCode(String superCode) {
		this.superCode = superCode;
	}

}
