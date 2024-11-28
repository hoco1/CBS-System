package com.example.CBS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CBS.model.Subscriber;
import com.example.CBS.repository.SubscriberRepository;

@Service
public class SubscriberService {
	private SubscriberRepository subscriberRepository;
	
	@Autowired
	public SubscriberService(SubscriberRepository subscriberRepository) {
		this.subscriberRepository = subscriberRepository;
	}
	
	public Subscriber RegisterSubscriber(Subscriber subscriber) {
		return subscriberRepository.save(subscriber);
	}
}
