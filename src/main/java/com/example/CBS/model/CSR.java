package com.example.CBS.model;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class CSR {
	
    @Id
    @Column(name = "csr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  csrId;

    @Column(name = "name_CSR")
    private String csrName;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "available")
    private Boolean available;

    @OneToMany(mappedBy = "csr", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<CSRAuthority> csrAuthorities;

	public Integer getCsrId() {
		return csrId;
	}

	public void setCsrId(Integer  csrId) {
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

	public List<CSRAuthority> getCsrAuthorities() {
		return csrAuthorities;
	}

	public void setCsrAuthorities(List<CSRAuthority> csrAuthorities) {
		this.csrAuthorities = csrAuthorities;
	}
	
	
	
}
