package com.example.CBS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CBS.exception.OfferExclusionException;
import com.example.CBS.model.Offer;
import com.example.CBS.model.Subscriber;
import com.example.CBS.model.SubscriberOffer;
import com.example.CBS.repository.SubscriberOfferRepository;

@Service
public class SubscriberOfferService {
	private SubscriberOfferRepository subscriberOfferRepository;
	private SubscriberService subscriberService;
	private OfferService offerService;
	
	@Autowired
	public SubscriberOfferService(SubscriberOfferRepository subscriberOfferRepository) {
		this.subscriberOfferRepository=subscriberOfferRepository;
	}
	
	public SubscriberOffer buyOffer(SubscriberOffer subscriberOffer,Offer offer , Subscriber subscriber) {
		
		SubscriberOffer subOffer = subscriberOfferRepository.findBySubscriberAndOffer(subscriber, offer);
		
		if(subOffer!=null) {
			throw new OfferExclusionException("Subscriber has already purchased this offer.");
		}
		return subscriberOfferRepository.save(subscriberOffer);
	}
	
	public SubscriberOffer usedOffer(Offer offer,Subscriber subscriber,Integer usedAmount) {
		

		SubscriberOffer subscriberOffers = subscriberOfferRepository.findBySubscriberAndOffer(subscriber, offer);
		
		subscriberOffers.usedOffer(usedAmount);

		return subscriberOfferRepository.save(subscriberOffers);
	}
}
