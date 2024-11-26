package com.example.CBS.dto;

import java.util.List;

public class CSRDTO {
	
	private String name;
	private String phoneNumber;
	private boolean available;
	private List<CSRAuthorityDTO> authorities;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public List<CSRAuthorityDTO> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<CSRAuthorityDTO> authorities) {
		this.authorities = authorities;
	}
	
	
	
}
