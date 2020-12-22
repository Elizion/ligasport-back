package com.core.ligasport.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teams")
public class Team extends UserDateAudit {
	   
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "league_id", nullable = false)
    private League league;

    @Size(max = 20)
    private String gender;
    
    private Integer sequence;
    
    @Size(max = 200)
    private String name;
    
    private Date dateFoundation;        

    @Size(max = 20)
    private String colorUniformA;
    
    @Size(max = 20)
    private String colorUniformB;
        
    private Boolean state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

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

	public String getColorUniformA() {
		return colorUniformA;
	}

	public void setColorUniformA(String colorUniformA) {
		this.colorUniformA = colorUniformA;
	}

	public String getColorUniformB() {
		return colorUniformB;
	}

	public void setColorUniformB(String colorUniformB) {
		this.colorUniformB = colorUniformB;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}                       
	       
}