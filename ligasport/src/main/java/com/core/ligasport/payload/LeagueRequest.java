package com.core.ligasport.payload;

public class LeagueRequest {
    
	private String name;
    
    private String category;
    
    private Boolean state;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
	   
}
