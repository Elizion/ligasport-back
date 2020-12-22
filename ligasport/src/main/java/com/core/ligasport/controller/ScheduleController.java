package com.core.ligasport.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.ligasport.payload.ScheduleRequest;
import com.core.ligasport.payload.ScheduleResponse;
import com.core.ligasport.security.CurrentUser;
import com.core.ligasport.security.UserPrincipal;
import com.core.ligasport.service.ScheduleService;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

	@Autowired
    private ScheduleService scheduleService;	

	@PostMapping("/{customerId}/{leagueId}")    
    public ScheduleResponse createSchedule(
    		@CurrentUser UserPrincipal currentUser,
    		@PathVariable Long customerId,
    		@PathVariable Long leagueId,
    		@Valid @RequestBody ScheduleRequest scheduleRequest) 
    {
    	
		ScheduleResponse scheduleResponse = this.scheduleService.createSchedule(customerId, leagueId, scheduleRequest, currentUser);
        
		return scheduleResponse;
		
    }

}
