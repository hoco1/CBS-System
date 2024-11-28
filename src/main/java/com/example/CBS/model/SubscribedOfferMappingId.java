package com.example.CBS.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SubscribedOfferMappingId implements Serializable{
	
	@Column(name="subscriber_id")
	private int subscriberId;
	
	@Column(name="offer_id")
	private int offerId;
	
	public SubscribedOfferMappingId() {
		
	}
	
	public SubscribedOfferMappingId(int subscriberId,int offerId) {
		this.subscriberId=subscriberId;
		this.offerId=offerId;
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
