package com.example.CBS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CBS.model.SubscriberOffer;
import com.example.CBS.repository.SubscriberOfferRepository;

@Service
public class SubscriberOfferService {
	private SubscriberOfferRepository subscriberOfferRepository;
	
	@Autowired
	public SubscriberOfferService(SubscriberOfferRepository subscriberOfferRepository) {
		this.subscriberOfferRepository=subscriberOfferRepository;
	}
	
	public SubscriberOffer buyOffer(SubscriberOffer subscriberOffer) {
		return subscriberOfferRepository.save(subscriberOffer);
	}
}
