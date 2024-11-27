package com.example.CBS.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CBS.exception.InvalidInputException;
import com.example.CBS.model.CSR;

import com.example.CBS.model.CSRAuthority.Role;
import com.example.CBS.service.CSRAuthorityService;
import com.example.CBS.service.CSRService;

@RestController()
@RequestMapping("/api")
public class CSRAuthorityController {
	
	private final CSRAuthorityService authorityServiceCSR;
	private final CSRService serviceCSR;
	
	@Autowired
	public CSRAuthorityController(CSRAuthorityService authorityServiceCSR,CSRService serviceCSR) {
		this.authorityServiceCSR=authorityServiceCSR;
		this.serviceCSR=serviceCSR;
	}
	
	@PostMapping("/add/auth")
	public ResponseEntity<Map<String,Object>> AddAuth(@RequestBody Map<String, Object> requestBody){
		// Extract the "nameCSR"
		if(!requestBody.containsKey("nameCSR") || !requestBody.containsKey("auth")) {
			throw new InvalidInputException("nameCSR / auth must be provided in the request body.");
		}
		
		Object nameCSR = requestBody.get("nameCSR");
		CSR csr = serviceCSR.findByCsrName((String) nameCSR);
		Object authValue = requestBody.get("auth");
		if(!(authValue instanceof List)) {
			throw new InvalidInputException("The value for 'auth' must be a List of valid enum values");
		}
		
		List<?> authList = (List<?>) authValue;
		
		// Parse and validation each value in the list
		List<Role> parsedAuthRoles = new ArrayList<>();
		for(Object authItem:authList) {
			if(!(authItem instanceof String)){
				throw new InvalidInputException("The value for 'auth' must be a List of valid enum values");
			}
			try {
				Role role = Role.valueOf((String) authItem);
				parsedAuthRoles.add(role);
			}catch(IllegalArgumentException e) {
				throw new IllegalArgumentException("The value for 'auth' must be a List of valid enum values");
			}
		}
		
		Map<String,Object> response = Map.of("CSR NAME :",nameCSR,
											 "Roles :",authorityServiceCSR.addAuth(csr, parsedAuthRoles));
		return ResponseEntity.ok(response);
		
	}
}
