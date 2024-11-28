package com.example.CBS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CBS.model.Offer;
import com.example.CBS.service.OfferService;

@RestController()
@RequestMapping("/api")
public class OfferController {
	
	private final OfferService offerService;
	
	@Autowired
	public OfferController(OfferService offerService) {
		this.offerService=offerService;
	}
	
	@PostMapping("/create/offer")
	public Offer createOffer(@RequestBody Offer offer) {
		return offerService.createOffer(offer);
	}
	
}
