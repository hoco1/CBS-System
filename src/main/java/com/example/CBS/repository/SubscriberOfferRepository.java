package com.example.CBS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.CBS.model.Offer;
import com.example.CBS.model.Subscriber;
import com.example.CBS.model.SubscriberOffer;

public interface SubscriberOfferRepository extends JpaRepository<SubscriberOffer,Integer> {
	@Query("SELECT e FROM SubscriberOffer e WHERE e.subscriber=:subscriber AND e.offer=:offer")
	SubscriberOffer findBySubscriberAndOffer(@Param("subscriber") Subscriber subscriber,
												   @Param("offer") Offer offer);
}
