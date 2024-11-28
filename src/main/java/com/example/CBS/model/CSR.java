package com.example.CBS.model;

import java.util.Set;
import jakarta.persistence.*;

@Entity
public class CSR {

    @Id
    @Column(name = "csr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer csrId;

    @Column(name = "name_CSR")
    private String csrName;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "available")
    private Boolean available;

    @ManyToMany
    @JoinTable(
        name = "csr_authority_mapping", // Join table name
        joinColumns = @JoinColumn(name = "csr_id"), // Foreign key for CSR
        inverseJoinColumns = @JoinColumn(name = "authority_id") // Foreign key for CSRAuthority
    )
    private Set<CSRAuthority> csrAuthorities;
    
    @OneToMany(mappedBy="createdByCSR",cascade = CascadeType.ALL)
    private Set<Offer> offers;
    
    public Integer getCsrId() {
        return csrId;
    }

    public void setCsrId(Integer csrId) {
        this.csrId = csrId;
    }

    public String getCsrName() {
        return csrName;
    }

    public void setCsrName(String csrName) {
        this.csrName = csrName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Set<CSRAuthority> getCsrAuthorities() {
        return csrAuthorities;
    }

    public void setCsrAuthorities(Set<CSRAuthority> csrAuthorities) {
        this.csrAuthorities = csrAuthorities;
    }
    
    public void addAuthority(CSRAuthority authority) {
        this.csrAuthorities.add(authority);
        authority.getCsrs().add(this);
    }
}
