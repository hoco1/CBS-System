package com.example.CBS.model;

public enum SubscriberType {
	PREPAID("0","PrePaid"),
	POSTPAID("1","Postpaid");
	
	private final String code;
	private final String lable;
	
	SubscriberType(String code,String lable) {
		this.code=code;
		this.lable=lable;
	}

	public String getCode() {
		return code;
	}

	public String getLable() {
		return lable;
	}
		
}
