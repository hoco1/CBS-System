package com.example.CBS.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Subscriber {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subscriber_id")
	private int subscriberId;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="msisdn")
	private String msisdn;
	
	@Convert(converter = SubscriberTypeConverter.class)
	@Column(name="subscriber_type")
	private SubscriberType subscriberType;
	
	@Column(name="credit_limit")
	private BigDecimal creditLimit;
	
	@Column(name="prepayment")
	private BigDecimal prepayment;
	
//	@OneToMany(mappedBy="subscriber",cascade=CascadeType.ALL,orphanRemoval=true)
//	private Set<SubscriberOffer> subscriberOffers = new HashSet<>();

	public int getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(int subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public SubscriberType getSubscriberType() {
		return subscriberType;
	}

	public void setSubscriberType(SubscriberType subscriberType) {
		this.subscriberType = subscriberType;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public BigDecimal getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(BigDecimal prepayment) {
		this.prepayment = prepayment;
	}

//	public Set<SubscriberOffer> getSubscriberOffers() {
//		return subscriberOffers;
//	}
//
//	public void setSubscriberOffers(Set<SubscriberOffer> subscriberOffers) {
//		this.subscriberOffers = subscriberOffers;
//	}
//	
//	public void addOffer(Offer offer) {
//		SubscriberOffer offers = new SubscriberOffer(this,offer);
//		subscriberOffers.add(offers);
//		offer.getSubscriberOffers().add(offers);
//	}
	
}
