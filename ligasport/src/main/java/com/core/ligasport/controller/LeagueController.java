package com.core.ligasport.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.core.ligasport.payload.LeagueRequest;
import com.core.ligasport.security.CurrentUser;
import com.core.ligasport.security.UserPrincipal;
import com.core.ligasport.service.LeagueService;

@RestController
@RequestMapping("/api/league")
public class LeagueController {
	
    @Autowired
    private LeagueService leagueService;
    
    @PostMapping("/{customerId}")    
    public void castVote(
    		@CurrentUser UserPrincipal currentUser,
    		@PathVariable Long customerId,
    		@Valid @RequestBody LeagueRequest leagueRequest) 
    {
    	
        	this.leagueService.createLeague(customerId, leagueRequest, currentUser);
        
    }
	
}
