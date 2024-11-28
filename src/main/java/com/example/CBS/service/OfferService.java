package com.example.CBS.service;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.CBS.model.CSR;
import com.example.CBS.model.Offer;
import com.example.CBS.model.Subscriber;
import com.example.CBS.repository.OfferRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OfferService {
	
	private OfferRepository offerRepository;
	private CSRService serviceCSR;
	@Autowired
	public OfferService(OfferRepository offerRepository,CSRService serviceCSR){
		this.offerRepository=offerRepository;
		this.serviceCSR=serviceCSR;
	}
	
	public Offer createOffer(Offer offer) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName();
		CSR csr = serviceCSR.findByCsrName(username);
		offer.setCreatedByCSR(csr);
		return offerRepository.save(offer);
	}
	
	public Offer findById(Integer offerId) {
		return offerRepository.findById(offerId)
				.orElseThrow(() -> new EntityNotFoundException("offer not found"));
	}
}
