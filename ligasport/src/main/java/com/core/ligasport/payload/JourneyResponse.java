package com.core.ligasport.payload;

import java.util.Date;
import java.util.List;

public class JourneyResponse {

    private String name;      
    private Date date;
    private String type;       
    private List<GameResponse> listMatch;
    private Integer totalMatch;
    private Integer sequence;    
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<GameResponse> getListMatch() {
		return listMatch;
	}
	public void setListMatch(List<GameResponse> listMatch) {
		this.listMatch = listMatch;
	}
	public Integer getTotalMatch() {
		return totalMatch;
	}
	public void setTotalMatch(Integer totalMatch) {
		this.totalMatch = totalMatch;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
       
}
