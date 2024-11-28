package com.example.CBS.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Offer {

    public enum OfferTypes {
        PRIMARY,
        SECONDARY;
    }
    
    public Offer() {}

    @Id
    @Column(name = "offer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int offerId;

    @Column(name = "offer_name")
    private String offerName;

    @Column(name = "data_limitMB")
    private BigDecimal dataLimitMB;

    @Column(name = "validity_days")
    private int validityDays;

    @Enumerated(EnumType.STRING)
    @Column(name = "offer_type")
    private OfferTypes offerType;

    @ManyToOne
    @JoinColumn(name = "created_byCSRId", nullable = false)
    private CSR createdByCSR;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SubscriberOffer> subscriberOffers = new HashSet<>();

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public BigDecimal getDataLimitMB() {
		return dataLimitMB;
	}

	public void setDataLimitMB(BigDecimal dataLimitMB) {
		this.dataLimitMB = dataLimitMB;
	}

	public int getValidityDays() {
		return validityDays;
	}

	public void setValidityDays(int validityDays) {
		this.validityDays = validityDays;
	}

	public OfferTypes getOfferType() {
		return offerType;
	}

	public void setOfferType(OfferTypes offerType) {
		this.offerType = offerType;
	}

	public CSR getCreatedByCSR() {
		return createdByCSR;
	}

	public void setCreatedByCSR(CSR createdByCSR) {
		this.createdByCSR = createdByCSR;
	}

	public Set<SubscriberOffer> getSubscriberOffers() {
		return subscriberOffers;
	}

	public void setSubscriberOffers(Set<SubscriberOffer> subscriberOffers) {
		this.subscriberOffers = subscriberOffers;
	}
    
    
}
