package com.example.CBS.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CSR_authority")
public class CSRAuthority {
	
    public enum Role {
        ROLE_VIEW_ONLY,
        ROLE_OFFER_MANAGEMENT,
        ROLE_FAMILY_GROUP_MANAGEMENT,
        ROLE_CSR_MANAGEMENT;
    	
        @Override
        public String toString() {
            return name().replace("ROLE_", "");
        }
    }

    @Id
    @Column(name = "authority_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorityId;

    @ManyToOne
    @JoinColumn(name = "csr_id", nullable = false)
    private CSR csr;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

	public int getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}

	public CSR getCsr() {
		return csr;
	}

	public void setCsr(CSR csr) {
		this.csr = csr;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "CSRAuthority [authorityId=" + authorityId + ", csr=" + csr + ", role=" + role + "]";
	}
	
	

}
