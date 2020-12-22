package com.core.ligasport.payload;

import java.util.Date;

public class XlsTeamDataRequest {
    
	private Integer sequence;
    
    private String name;
    
    private Date dateFoundation;    
    
    private Boolean state;
    
    private String[] uniforms;    
	
    public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateFoundation() {
		return dateFoundation;
	}

	public void setDateFoundation(Date dateFoundation) {
		this.dateFoundation = dateFoundation;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}       
    
	public String[] getUniforms() {
		return uniforms;
	}

	public void setUniforms(String[] uniforms) {
		this.uniforms = uniforms;
	}

	
}
