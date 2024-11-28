package com.example.CBS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CBS.model.Offer;
import com.example.CBS.model.Subscriber;
import com.example.CBS.service.SubscriberService;

@RestController
@RequestMapping("/api")
public class SubscriberController {
	
	private SubscriberService subscriberService;
	
	@Autowired
	public SubscriberController(SubscriberService subscriberService) {
		this.subscriberService=subscriberService;
	}
	
	@PostMapping("/register/subscriber")
	public Subscriber createOffer(@RequestBody Subscriber subscriber) {
		return subscriberService.RegisterSubscriber(subscriber);
	}
}
