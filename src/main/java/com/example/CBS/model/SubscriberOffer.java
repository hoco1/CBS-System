package com.example.CBS.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class SubscriberOffer {
    @EmbeddedId
    private SubscribedOfferMappingId id;
   
    @Column(name = "data_usedMB")
    private BigDecimal dataUsedMB = BigDecimal.ZERO;
    
    @Column(name = "data_unusedMB")
    private BigDecimal dataUnusedMB=BigDecimal.ZERO;
    
    @Column(name = "create_time")
    private LocalDate createTime;
    
    @Column(name = "expiry_time")
    private LocalDate expiryTime;
    
    @ManyToOne
    @MapsId("subscriberId")
    @JoinColumn(name="subscriber_id")
    private Subscriber subscriber;
    
    @ManyToOne
    @MapsId("offerId")
    @JoinColumn(name="offer_id")
    private Offer offer;
    
    public SubscriberOffer() {
    	
    }
	public SubscriberOffer(Subscriber subscriber, Offer offer) {
		this.subscriber = subscriber;
		this.offer = offer;
		this.id = new SubscribedOfferMappingId(subscriber.getSubscriberId(),offer.getOfferId());
	}
	
	public void usedOffer(Integer amount) {
	    // Ensure that amount is not null or negative
	    if (amount != null && amount > 0) {
	        this.dataUsedMB = this.dataUsedMB.add(BigDecimal.valueOf(amount));  // Reassign the result
	        this.dataUnusedMB = this.dataUnusedMB.subtract(BigDecimal.valueOf(amount));  // Reassign the result
	    }
	}


	public BigDecimal getDataUsedMB() {
		return dataUsedMB;
	}

	public void setDataUsedMB(BigDecimal dataUsedMB) {
		this.dataUsedMB = dataUsedMB;
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

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public BigDecimal getDataUnusedMB() {
		return dataUnusedMB;
	}
	public void setDataUnusedMB(BigDecimal dataUnusedMB) {
		this.dataUnusedMB = dataUnusedMB;
	}
    
    
}
