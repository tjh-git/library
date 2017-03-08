package com.simple.bsp.usernewstype.po;

public class UserNewsType {
	
	private String userNewsTypeId;	//用户新闻类型表id
	private String userId;			//用户Id
	private String newsTypeCode;	//新闻类型id
	public String getUserNewsTypeId() {
		return userNewsTypeId;
	}
	public void setUserNewsTypeId(String userNewsTypeId) {
		this.userNewsTypeId = userNewsTypeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNewsTypeCode() {
		return newsTypeCode;
	}
	public void setNewsTypeCode(String newsTypeCode) {
		this.newsTypeCode = newsTypeCode;
	}
	public String getNewsTypeValue() {
		return newsTypeValue;
	}
	public void setNewsTypeValue(String newsTypeValue) {
		this.newsTypeValue = newsTypeValue;
	}
	public String getNewsTypeName() {
		return newsTypeName;
	}
	public void setNewsTypeName(String newsTypeName) {
		this.newsTypeName = newsTypeName;
	}
	private String newsTypeValue;	//新闻类型的值
	private String newsTypeName;	//新闻类型的名字
	

}
