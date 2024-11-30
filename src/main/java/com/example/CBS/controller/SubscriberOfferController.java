package com.example.CBS.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CBS.dto.SubscriberOfferDTO;
import com.example.CBS.model.Offer;
import com.example.CBS.model.Subscriber;
import com.example.CBS.model.SubscriberOffer;
import com.example.CBS.service.OfferService;
import com.example.CBS.service.SubscriberOfferService;
import com.example.CBS.service.SubscriberService;

@RestController
@RequestMapping("/api")
public class SubscriberOfferController {
	
	private SubscriberOfferService subscriberOfferService;
	private OfferService offerService;
	private SubscriberService subscriberService;
	
	@Autowired
	public SubscriberOfferController(SubscriberOfferService subscriberOfferService,
			OfferService offerService,
			SubscriberService subscriberService) {
		this.subscriberOfferService=subscriberOfferService;
		this.offerService=offerService;
		this.subscriberService=subscriberService;
	}
	
	@PostMapping("/offer/purchase")
	public ResponseEntity<Map<String,Object>> purchaseOffer(@RequestBody SubscriberOfferDTO dto) {
		Subscriber subscriber = subscriberService.findById(dto.getSubscriberId());
		Offer offer = offerService.findById(dto.getOfferId());
		SubscriberOffer subscriberOffer = new SubscriberOffer(subscriber,offer);
		subscriberOffer.setDataUsedMB(dto.getDataUsedMB());
		subscriberOffer.setCreateTime(dto.getCreateTime() !=null ? dto.getCreateTime():LocalDate.now());
		subscriberOffer.setExpiryTime(dto.getExpiryTime() !=null ? 
				dto.getExpiryTime():
				LocalDate.now().plusDays(offer.getValidityDays()));
		// after test i put it here :)
		subscriberOffer.setDataUsedMB(BigDecimal.valueOf(0.00));
		
		subscriberOffer.setDataUnusedMB(offer.getDataLimitMB());
		subscriberOfferService.buyOffer(subscriberOffer,offer,subscriber);
        Map<String,Object> response = Map.of("message","offer buyed sucss",
				"offer id",dto.getSubscriberId());
        return ResponseEntity.ok(response) ;
		 
	}
	
}
