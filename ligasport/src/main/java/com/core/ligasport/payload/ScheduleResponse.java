package com.core.ligasport.payload;

import java.util.List;

public class ScheduleResponse {

	private List<JourneyResponse> listJourney;
		
	public List<JourneyResponse> getListJourney() {
		return listJourney;
	}
	public void setListJourney(List<JourneyResponse> listJourney) {
		this.listJourney = listJourney;
	}
			
}
