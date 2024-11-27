package com.example.CBS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CBS.model.CSR;

@Repository
public interface CSRRepository extends JpaRepository<CSR,Integer> {
	
	Optional<CSR> findBycsrName(String CSRName);
}
