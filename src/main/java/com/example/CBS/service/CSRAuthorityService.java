package com.example.CBS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CBS.model.CSR;
import com.example.CBS.model.CSRAuthority;
import com.example.CBS.model.CSRAuthority.Role;
import com.example.CBS.repository.CSRAuthorityRepository;


@Service
public class CSRAuthorityService {
	
	private CSRAuthorityRepository authorityRepositoryCSR;

	@Autowired
	public CSRAuthorityService(CSRAuthorityRepository authorityRepositoryCSR) {
		this.authorityRepositoryCSR=authorityRepositoryCSR;
	}
	
	public List<String> addAuth(CSR csr, List<Role> auths) {
        List<String> authCSR = authorityRepositoryCSR.findCSRByCSRAuthorityField(csr.getCsrId());

        for (Role auth : auths) {
            if (!authCSR.contains(auth.formatRole(true))) {
                
            	CSRAuthority existingAuthority = authorityRepositoryCSR.findByRole(auth);
            	
            	if(existingAuthority == null) {
            		CSRAuthority newAuthority = new CSRAuthority();
            		newAuthority.setRole(auth);
            		authorityRepositoryCSR.save(newAuthority);
            		newAuthority.addCsr(csr);
            		authorityRepositoryCSR.save(newAuthority);
            	}else {
            		existingAuthority.addCsr(csr);
            		authorityRepositoryCSR.save(existingAuthority);
            	}
            	
            	authCSR.add(auth.formatRole(true));
            }
        }

        return authCSR;
    }	
}
