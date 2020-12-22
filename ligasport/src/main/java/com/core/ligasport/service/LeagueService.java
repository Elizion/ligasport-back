package com.core.ligasport.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.ligasport.model.Customer;
import com.core.ligasport.model.League;
import com.core.ligasport.payload.LeagueRequest;
import com.core.ligasport.repository.CustomerRepository;
import com.core.ligasport.repository.LeagueRepository;
import com.core.ligasport.security.UserPrincipal;
import com.core.ligasport.util.BadRequestException;
import com.core.ligasport.util.ResourceNotFoundException;

@Service
public class LeagueService {
	    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private LeagueRepository leagueRepository;
    
	private static final Logger logger = LoggerFactory.getLogger(LeagueService.class);
	
	public void createLeague(Long customerId, LeagueRequest leagueRequest, UserPrincipal currentUser) {
    			
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));		
		
		League league = new League();
		
		league.setName(leagueRequest.getName());
		league.setCategory(leagueRequest.getCategory());
		league.setState(leagueRequest.getState());
		league.setCustomer(customer);
		
        try {
        	
        	league = this.leagueRepository.save(league);        	
        	
        } catch (Exception e) {
        	
        	logger.info(e.toString());
            throw new BadRequestException("Sorry! Bad Request");
            
        }		
		
    }

}
