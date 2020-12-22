package com.core.ligasport.payload;

import java.util.List;

public class XlsTeamRequest {

    private Long customerId;
    
    private Long leagueId;    

    private String gender;            

    private List<XlsTeamDataRequest> xlsTeamDataRequest;   
    
    private String sheetName;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}
   
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<XlsTeamDataRequest> getXlsTeamDataRequest() {
		return xlsTeamDataRequest;
	}

	public void setXlsTeamDataRequest(List<XlsTeamDataRequest> xlsTeamDataRequest) {
		this.xlsTeamDataRequest = xlsTeamDataRequest;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
        
}
