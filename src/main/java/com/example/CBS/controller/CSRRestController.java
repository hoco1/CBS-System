package com.example.CBS.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CBS.dto.CSRAuthorityDTO;
import com.example.CBS.dto.CSRDTO;
import com.example.CBS.exception.CSRNotFoundException;
import com.example.CBS.exception.InvalidInputException;
import com.example.CBS.model.CSR;
import com.example.CBS.model.CSRAuthority;
import com.example.CBS.service.CSRService;

@RestController
@RequestMapping("/api")
public class CSRRestController {

    private final CSRService serviceCSR;

    @Autowired
    public CSRRestController(CSRService serviceCSR) {
        this.serviceCSR = serviceCSR;
    }

    @GetMapping("/csrs")
    public List<CSRDTO> findAll() {
        List<CSR> csrList = serviceCSR.findAll();
        return csrList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/csrs/by-id")
    public CSRDTO getCSR(@RequestBody Map<String, String> requestBody) {
        try {
            String csrIdStr = requestBody.get("csrId");
            if (csrIdStr == null) {
                throw new InvalidInputException("csrId must be provided in the request body.");
            }
            int csrId = Integer.parseInt(csrIdStr);
            
            CSR csr = serviceCSR.findById(csrId);
            
            return convertToDTO(csr);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid ID provided. ID must be an integer.");
        }
    }

    @PostMapping("/csrs")
    public CSRDTO addCSR(@RequestBody CSR theCSR) {
        CSR csr = serviceCSR.save(theCSR);
        return convertToDTO(csr);
    }

    @DeleteMapping("/csrs/by-id")
    public ResponseEntity<Map<String,Object>> deleteCSR(@RequestBody Map<String, String> requestBody) {
        try {
            String csrIdStr = requestBody.get("csrId");
            if (csrIdStr == null) {
                throw new InvalidInputException("csrId must be provided in the request body.");
            }
            int csrId = Integer.parseInt(csrIdStr);
            
            CSR csr = serviceCSR.findById(csrId);
            if(csr == null) {
            	throw new CSRNotFoundException("Did not find CSR with ID - " + csrId);
            }
            serviceCSR.deleteById(csrId);
            
            Map<String,Object> response = Map.of("message","Deleted CSR successfully",
            										"csrId",csrId);
            return ResponseEntity.ok(response) ;
            
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid ID provided. ID must be an integer.");
        }
    }

    @PutMapping("/csrs")
    public CSRDTO updateCSR(@RequestBody CSR theCSR) {
    	try {
        	if(theCSR.getCsrId() == null) {
        		throw new InvalidInputException("csrId must be provided in the request body.");
        	}
        	
        	CSR existingCSR = serviceCSR.findById(theCSR.getCsrId());
        	
        	if(theCSR.getCsrName() != null && !theCSR.getCsrName().equals(existingCSR.getCsrName())) {
        		CSR csrWithSameName = serviceCSR.findByCsrName(theCSR.getCsrName());
        		if(csrWithSameName !=null) {
        			throw new InvalidInputException("CSR name already exists.");
        		}
        		existingCSR.setCsrName(theCSR.getCsrName());
        	}
        	
        	if(theCSR.getPhoneNumber() != null) {
        		existingCSR.setPhoneNumber(theCSR.getPhoneNumber());
        	}
        	
        	if(theCSR.isAvailable() != null) {
        		existingCSR.setAvailable(theCSR.isAvailable());
        	}
        	
        	if(theCSR.getPassword() != null) {
        		existingCSR.setPassword(theCSR.getPassword());
        	}
            CSR updatedCSR  = serviceCSR.update(existingCSR);
            return convertToDTO(updatedCSR);
            
    	}catch(NumberFormatException e) {
    		throw new InvalidInputException("Invalid ID provided. ID must be an integer.");
    	}

    }

    // Convert CSR entity to CSRDTO (for display purposes)
    private CSRDTO convertToDTO(CSR csr) {
        CSRDTO dto = new CSRDTO();
        dto.setName(csr.getCsrName());
        dto.setPhoneNumber(csr.getPhoneNumber());
        dto.setAvailable(csr.isAvailable());
        
        if (csr.getCsrAuthorities() != null && !csr.getCsrAuthorities().isEmpty()) {
        	CSRAuthorityDTO authorityDTO = convertToAuthorityDTO(csr.getCsrAuthorities());
        	dto.setAuthorities(List.of(authorityDTO));
        }
        return dto;
    }
    
    
    private CSRAuthorityDTO convertToAuthorityDTO(Set<CSRAuthority> authorities) {
        CSRAuthorityDTO dto = new CSRAuthorityDTO();
        dto.setRoles(authorities.stream()
        			.map(CSRAuthority::getRole)
        			.collect(Collectors.toList()));
        return dto;
    }
}
