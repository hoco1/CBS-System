package com.example.CBS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CBS.model.CSR;
import com.example.CBS.model.CSRAuthority;
@Repository
public interface CSRRepository extends JpaRepository<CSR,Integer> {
	
	Optional<CSR> findBycsrName(String CSRName);
}
