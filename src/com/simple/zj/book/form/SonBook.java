package com.simple.zj.book.form;

import com.simple.zj.book.po.Book;

public class SonBook extends Book {
    
	private String startdate;
	private String enddate;
	private String tsyyName;
	private String tslxName;
	
	public String getTsyyName() {
		return tsyyName;
	}
	public void setTsyyName(String tsyyName) {
		this.tsyyName = tsyyName;
	}
	public String getTslxName() {
		return tslxName;
	}
	public void setTslxName(String tslxName) {
		this.tslxName = tslxName;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	
}
