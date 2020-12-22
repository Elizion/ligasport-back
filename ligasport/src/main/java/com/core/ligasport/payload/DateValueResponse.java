package com.core.ligasport.payload;

import java.util.Date;

public class DateValueResponse {
	
	private Date date;
	private String season;
	private Double value;
	private String state;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}		
	
}
