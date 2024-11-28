package com.example.CBS.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

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
            return formatRole(false);
        }
        
        public String formatRole(boolean keepRolePrefix) {
        	if(keepRolePrefix) {
        		return name();
        	}else {
        		return name().replace("ROLE_", "");
        	}
        }
    }

    @Id
    @Column(name = "authority_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @ManyToMany(mappedBy = "csrAuthorities")
    @JsonBackReference
    private Set<CSR> csrs = new HashSet<>();

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<CSR> getCsrs() {
        return csrs;
    }

    public void setCsrs(Set<CSR> csrs) {
        this.csrs = csrs;
    }
    
    public void addCsr(CSR csr) {
        this.csrs.add(csr);
        csr.getCsrAuthorities().add(this);
    }
}
