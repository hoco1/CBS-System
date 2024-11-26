package com.example.CBS.dto;

import com.example.CBS.model.CSR;
import com.example.CBS.model.CSRAuthority.Role;

public class CSRAuthorityDTO {
	
//	private Long authorityId;
//	private Long idCSR;
	private Role role;
	
	public CSRAuthorityDTO(){
		
	}
//	CSRAuthorityDTO(Long idCSR,Role role){
//		this.idCSR = idCSR;
//		this.role = role;
//	}
//	public Long getAuthorityId() {
//		return authorityId;
//	}
//	public void setAuthorityId(Long authorityId) {
//		this.authorityId = authorityId;
//	}
//	public Long getIdCSR() {
//		return idCSR;
//	}
//	public void setIdCSR(Long idCSR) {
//		this.idCSR = idCSR;
//	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
