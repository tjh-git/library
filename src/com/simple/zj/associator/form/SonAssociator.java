package com.simple.zj.associator.form;

import com.simple.zj.associator.po.Associator;

public class SonAssociator extends Associator {
	
	private String startdate;
	private String enddate;
	private String cardtypeName;
	private String stateName;
	public String getCardtypeName() {
		return cardtypeName;
	}
	public void setCardtypeName(String cardtypeName) {
		this.cardtypeName = cardtypeName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
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
