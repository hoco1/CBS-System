package com.example.CBS.dto;

import java.math.BigDecimal;
import java.time.LocalDate;


public class SubscriberOfferDTO {
    
    private BigDecimal dataUsedMB;
    private BigDecimal dataUnusedMB;
    private LocalDate createTime;
    private LocalDate expiryTime;
    private int subscriberId;
    private int offerId;
    
	public BigDecimal getDataUsedMB() {
		return dataUsedMB;
	}
	public void setDataUsedMB(BigDecimal dataUsedMB) {
		this.dataUsedMB = dataUsedMB;
	}
	
	public BigDecimal getDataUnusedMB() {
		return dataUnusedMB;
	}
	public void setDataUnusedMB(BigDecimal dataUnusedMB) {
		this.dataUnusedMB = dataUnusedMB;
	}
	
	public LocalDate getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDate createTime) {
		this.createTime = createTime;
	}
	public LocalDate getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(LocalDate expiryTime) {
		this.expiryTime = expiryTime;
	}
	public int getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(int subscriberId) {
		this.subscriberId = subscriberId;
	}
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
    
    
}
