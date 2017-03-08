/**
 * 
 */
package com.simple.bsp.org.po;

import java.io.Serializable;

/**
 * @author simple
 *
 */
public class Org implements Serializable{
	
	private static final long serialVersionUID = -486085841451424679L;
	
	private String orgId;		//机构ID
	private String orgCode;		//机构编码
	private String orgName;		//机构名称
	private String enable;		//机构状态(0:正常/1:禁用)
	private String orgAddress;	//机构地址
	private String orgDesc;		//机构描述
	private String orgReserve1;	//机构预留1
	private String orgReserve2;	//机构预留2
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	public String getOrgDesc() {
		return orgDesc;
	}
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	public String getOrgReserve1() {
		return orgReserve1;
	}
	public void setOrgReserve1(String orgReserve1) {
		this.orgReserve1 = orgReserve1;
	}
	public String getOrgReserve2() {
		return orgReserve2;
	}
	public void setOrgReserve2(String orgReserve2) {
		this.orgReserve2 = orgReserve2;
	}
}
