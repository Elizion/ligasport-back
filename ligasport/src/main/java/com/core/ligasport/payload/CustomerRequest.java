package com.core.ligasport.payload;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

public class CustomerRequest {

    @NotBlank
    private String name;
    
    @NotBlank
    private String apellidoPaterno;

    @NotBlank
    private String apellidoMaterno;
    
    @Lob
    private String avatar;
    
    @NotBlank
    private String email;

    @NotBlank
    private String cellphone;
    
    @NotBlank
    private String localphone;

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
    
}
