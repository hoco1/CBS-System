package com.example.CBS.service;

import org.springframework.stereotype.Service;
import com.example.CBS.repository.*;
import com.example.CBS.exception.CSRAlreadyExistsException;
import com.example.CBS.exception.CSRNotFoundException;
import com.example.CBS.model.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CSRService implements CrudService<CSR,Integer>{
	
	private CSRRepository repositoryCSR;
	
	@Autowired
	public CSRService(CSRRepository repositoryCSR) {
		this.repositoryCSR=repositoryCSR;
	}

	
    @Override
    public List<CSR> findAll() {
    	try {
    		return repositoryCSR.findAll();
    	}
    	catch(Exception e) {
    		throw new RuntimeException("An unexpected error occurred while retrieving the CSR. Please try again later.");
    	}
        
    }

    @Override
    public CSR findById(Integer id) {
    	try {
        	return repositoryCSR.findById(id)
                    .orElseThrow(() -> new CSRNotFoundException("Did not find CSR id - " + id));
    	}catch(CSRNotFoundException e) {
    		throw e;
    	}catch(Exception e) {
    		throw new RuntimeException("An unexpected error occurred.");
    	}

    }
    
    
    public CSR findByCsrName(String username) {
    	try {
    		return repositoryCSR.findBycsrName(username)
                    .orElseThrow(() -> new CSRNotFoundException("Did not find CSR id - " + username));
    	}catch(CSRNotFoundException e) {
    		throw e;
    	}catch(Exception e) {
    		throw new RuntimeException("An unexpected error occurred.");
    	}

    }

    @Override
    public CSR save(CSR theCSR) {
    	try {
            Optional<CSR> existingCSR = repositoryCSR.findBycsrName(theCSR.getCsrName());
            existingCSR.ifPresent(csr ->{
            	throw new CSRAlreadyExistsException("CSR with name " + theCSR.getCsrName() + " already exists.");
            });
        	return repositoryCSR.save(theCSR);
    	}catch(CSRAlreadyExistsException e) {
    		throw e;
    	}catch(Exception e) {
    		throw new RuntimeException("An unexpected error occurred.");
    	}

    }
    
    @Override
    public CSR update(CSR theCSR) {
    	try {
        	return repositoryCSR.save(theCSR);
    	}catch(Exception e) {
    		throw new RuntimeException("An unexpected error occurred.");
    	}

    }

    @Override
    public void deleteById(Integer theId) {
        try {
            repositoryCSR.deleteById(theId);
        }catch(Exception e) {
        	throw new RuntimeException("An unexpected error occurred.");
        }

    	
    }
    	
}
