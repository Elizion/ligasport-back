package com.core.ligasport.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customer extends UserDateAudit { 

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;
    
    @NotBlank
    @Size(max = 100)
    private String apellidoPaterno;

    @NotBlank
    @Size(max = 100)
    private String apellidoMaterno;
    
    @Lob
    private String avatar;
    
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 50)
    private String cellphone;
    
    @NotBlank
    @Size(max = 50)
    private String localphone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getLocalphone() {
		return localphone;
	}

	public void setLocalphone(String localphone) {
		this.localphone = localphone;
	}
            
    /*
	public void addLeague(League league) {
		leagues.add(league);
		league.setCustomer(this);
    }

    public void removeLeague(League league) {
    	leagues.remove(league);
    	league.setCustomer(null);
    }
    */         
}
