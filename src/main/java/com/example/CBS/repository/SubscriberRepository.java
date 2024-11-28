package com.example.CBS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CBS.model.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber,Integer> {

}
