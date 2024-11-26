package com.example.CBS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CBS.model.CSRAuthority;
@Repository
public interface CSRAuthorityRepository extends JpaRepository<CSRAuthority, Integer> {
	List<CSRAuthority> findByCsr_csrId(Integer csrId);
}
