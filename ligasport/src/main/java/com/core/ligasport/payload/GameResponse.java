package com.core.ligasport.payload;

import java.util.Date;

public class GameResponse {
	
	private String teamA;
	
	private String teamB;
	
	private Date hour;

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
	
}
