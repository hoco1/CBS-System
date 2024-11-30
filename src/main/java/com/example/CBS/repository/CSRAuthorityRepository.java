package com.example.CBS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CBS.model.CSRAuthority;
import com.example.CBS.model.CSRAuthority.Role;
@Repository
public interface CSRAuthorityRepository extends JpaRepository<CSRAuthority, Integer> {
	//List<CSRAuthority> findByCsr_csrId(Integer csrId);
	

    
	@Query(value ="SELECT authority.role " +
            "FROM CSR csr " +
            "JOIN csr_authority_mapping mapping ON csr.csr_id = mapping.csr_id " +
            "JOIN CSR_authority authority ON mapping.authority_id = authority.authority_id " +
            "WHERE csr.csr_id = :csrId", nativeQuery = true)
	List<String> findCSRByCSRAuthorityField(@Param("csrId") Integer csrId);

	CSRAuthority findByRole(Role auth);
}
