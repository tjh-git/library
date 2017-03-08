package com.simple.example.form;

import com.simple.example.po.RefundInfo;

public class RefundInfoForm extends RefundInfo{
	private String studentName;
	private String typeName;
	private String beginDate;
	private String endDate;
	private String studentNameF;
	private String idNo;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStudentNameF() {
		return studentNameF;
	}

	public void setStudentNameF(String studentNameF) {
		this.studentNameF = studentNameF;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
}
