package com.example.CBS.dto;

import java.util.List;

import com.example.CBS.model.CSRAuthority.Role;

public class CSRAuthorityDTO {

    private List<Role> roles; // Ensure this is a simple type like List<Role>

    public CSRAuthorityDTO() {
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
