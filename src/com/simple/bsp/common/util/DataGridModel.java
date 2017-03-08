/**
 * 
 */
package com.simple.bsp.common.util;

/**
 * @author simple 
 *
 */
public class DataGridModel implements java.io.Serializable{
	
	private static final long serialVersionUID = -4751923325064289240L;
	
	private int page; 		//当前页,名字必须为page
	private int rows ; 		//每页大小,名字必须为rows
	private String sidx; 	//排序字段
	private String sord; 	//排序规则
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getOrder() {
		return sord;
	}
	public void setord(String sord) {
		this.sord = sord;
	}
}
