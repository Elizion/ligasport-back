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
@Table(name = "games")
public class Game extends UserDateAudit {
	    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 200)
    private String teamA;
    
    @Size(max = 200)
    private String teamB;
    
    private Date hour;   
    
    private Boolean state;
        
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "journey_id", nullable = false)
    private Journey journey;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeamA() {
		return teamA;
	}

	public void setTeamA(String teamA) {
		this.teamA = teamA;
	}

	public String getTeamB() {
		return teamB;
	}

	public void setTeamB(String teamB) {
		this.teamB = teamB;
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}        
    
}
