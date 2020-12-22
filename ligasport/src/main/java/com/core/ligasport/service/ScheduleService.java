package com.core.ligasport.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.ligasport.model.Customer;
import com.core.ligasport.model.Game;
import com.core.ligasport.model.Journey;
import com.core.ligasport.model.League;
import com.core.ligasport.model.Schedule;
import com.core.ligasport.payload.ScheduleRequest;
import com.core.ligasport.payload.ScheduleResponse;
import com.core.ligasport.payload.JourneyResponse;
import com.core.ligasport.payload.GameResponse;
import com.core.ligasport.repository.CustomerRepository;
import com.core.ligasport.repository.GameRepository;
import com.core.ligasport.repository.JourneyRepository;
import com.core.ligasport.repository.LeagueRepository;
import com.core.ligasport.repository.ScheduleRepository;
import com.core.ligasport.repository.TeamRepository;
import com.core.ligasport.security.UserPrincipal;
import com.core.ligasport.util.BadRequestException;
import com.core.ligasport.util.ResourceNotFoundException;

@Service
public class ScheduleService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @Autowired
    private LeagueRepository leagueRepository;
    
    @Autowired
    private JourneyRepository journeyRepository;
    
    @Autowired
    private GameRepository gameRepository;
	
    @Autowired
    private TeamRepository teamRepository;
	
    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);	
	
    public Calendar convertDate (String dateStart) {    	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		Calendar cal = Calendar.getInstance();		
		Date date = null;    			
		try {			
			date = sdf.parse(dateStart);			
			cal.setTime(date);			     	   
		} catch (ParseException e) {
			e.printStackTrace();
		}		
    	return cal;    	
    }    	
		
    public Calendar verifyHolidayAssign(Calendar cal) {						    			
		try {										
            if (cal.get(Calendar.MONTH) == 3 && cal.get(Calendar.DAY_OF_MONTH) == 9 ||
	            cal.get(Calendar.MONTH) == 3 && cal.get(Calendar.DAY_OF_MONTH) == 10 ||
	            cal.get(Calendar.MONTH) == 3 && cal.get(Calendar.DAY_OF_MONTH) == 11 ||
	            cal.get(Calendar.MONTH) == 3 && cal.get(Calendar.DAY_OF_MONTH) == 12 ||
	            cal.get(Calendar.MONTH) == 12 && cal.get(Calendar.DAY_OF_MONTH) == 1) 
	         {   	         			         	
	         	cal.add(Calendar.WEEK_OF_MONTH, 1);
	         }     	    
		} catch (Exception e) {
			e.printStackTrace();
		}		
    	return cal;    	
    }
    
    public Calendar verifyHolidayStart(String dateStart) {    	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		Calendar cal = Calendar.getInstance();		
		Date date = null;    			
		try {			
			date = sdf.parse(dateStart);			
			cal.setTime(date);			
            if (cal.get(Calendar.MONTH) == 3 && cal.get(Calendar.DAY_OF_MONTH) == 9 ||
    	        cal.get(Calendar.MONTH) == 3 && cal.get(Calendar.DAY_OF_MONTH) == 10 ||
    	        cal.get(Calendar.MONTH) == 3 && cal.get(Calendar.DAY_OF_MONTH) == 11 ||
    	        cal.get(Calendar.MONTH) == 3 && cal.get(Calendar.DAY_OF_MONTH) == 12 ||
    	        cal.get(Calendar.MONTH) == 12 && cal.get(Calendar.DAY_OF_MONTH) == 1) 
	         {   	         			         	
	         	cal.add(Calendar.WEEK_OF_MONTH, 1);
	         }     	    
		} catch (ParseException e) {
			e.printStackTrace();
		}		
    	return cal;    	
    }
    
    public Calendar assignDate(Date date) {    					
		Calendar cal = Calendar.getInstance();		
		try {						
			cal.setTime(date);
	        cal.add(Calendar.WEEK_OF_MONTH, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}	
    	return cal;
    }
    
    private Calendar assignHourMatch(Date date, Integer hourMatch){    	
    	Calendar cal = Calendar.getInstance();    	    	    
		try {						
			cal.setTime(date);
			cal.add(Calendar.HOUR_OF_DAY, hourMatch);
		} catch (Exception e) {
			e.printStackTrace();
		}	
    	return cal;    	
    }
    
    public ScheduleResponse generateSchedule(String dateFrom, Long customerId, Long leagueId) {
    	
    	ScheduleResponse schedule = new ScheduleResponse();    	    	
    	
    	Integer N = this.teamRepository.countTeamsTrue(customerId, leagueId);    	
    	
    	if (N%2 == 0 ) {    		        
        	
    		Integer[] teams;    	
        	
    		String[][] matrizA;
        	String[][] matrizB;
        	String[][] matches;    	
        	
        	teams = new Integer[N];    			
        	
        	for (int i=0;i<N;i++) {
    			teams[i] = i+1;
    		}    	
        	        	
    		matrizA = new String[N-1][N/2];
    		matrizB = new String[N-1][N/2];
    		matches = new String[N-1][N/2];				    	
    		int cont = 0;
    		int cont2 = N-2;		
    		
    		try {			
    			
    			for(int i=0;i<N-1;i++) {
    				
    				for(int j=0;j<N/2;j++) {
    					
    					matrizA[i][j] = String.valueOf(teams[cont]);				
    					cont++;					
    					if(cont==(N-1)) {
    						cont=0;				
    					}
    					if(j==0) {
    						matrizB[i][j] = String.valueOf(N);				
    					} else {					
    						matrizB[i][j] = String.valueOf(teams[cont2]);
    						cont2--;
    						if(cont2==-1) {
    							cont2 = N-2;
    						}
    					}					
    					if(j==0) {				
    						if(i%2==0) {
    							matches[i][j] = matrizB[i][j] + "-" + matrizA[i][j];
    						} else {
    							matches[i][j] = matrizA[i][j] + "-" + matrizB[i][j];
    						}
    					} else { 
    						matches[i][j] = matrizA[i][j] + "-" + matrizB[i][j];				
    					}					
    				}								
    			}					
    			List<JourneyResponse> listJourney = new ArrayList<JourneyResponse>();
    			List<GameResponse> listGame = null;		
    			JourneyResponse journey = null;		
    			GameResponse match = null;			
    			Calendar cal = this.verifyHolidayStart(dateFrom);							
    			Calendar hour = null;							
    			String[] parts = null;
    			Integer c = null;	
    			Integer jorn = 1;			
    			Integer startHour = null;							
    			for(int i=0; i<N-1; i++) {				
    				listGame = new ArrayList<GameResponse>();				
    				journey = new JourneyResponse();							
    				journey.setName("J"+jorn);
    				journey.setDate(cal.getTime());
    				journey.setType("Short");
    				journey.setSequence(jorn);				
    				listJourney.add(journey);																							
    				c = jorn -1;						
    				startHour = 7;				
    				for(int j=0; j<N/2; j++) {				
    					match = new GameResponse();																
    					parts = matches[i][j].split("-");				
    					match.setTeamA(parts[0]);
    					match.setTeamB(parts[1]);				
    					hour = this.assignHourMatch(cal.getTime(), startHour ++);
    					match.setHour(hour.getTime());				
    					listGame.add(match);
    					journey.setTotalMatch(listGame.size());
    					listJourney.get(c).setListMatch(listGame);				
    				}										
    				journey.setTotalMatch(listGame.size());
    				cal = this.assignDate(cal.getTime());
    				cal = this.verifyHolidayAssign(cal);				
    				jorn++;			
    			}
    			schedule.setListJourney(listJourney);	
    		} catch(Exception e) {
    			logger.info(e.toString());
    		}
    	} else {
    		
    		throw new BadRequestException("No puede comenzar el calendario de juegos sin tener 12, 14, 16, 18 o 20 equipos en la liga.");
    		
    	}
    						
		return schedule;
		
    };
    
	public ScheduleResponse createSchedule(Long customerId, Long leagueId, ScheduleRequest scheduleRequest, UserPrincipal currentUser) {		
		
		ScheduleResponse scheduleResponse = null;				
		List<JourneyResponse> listJourneyResponse = null;					
		List<GameResponse> listMatchResponse = null;		
		Customer customer = null;		
		League league = null;				
        
		try {        	
    		
        	customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));	
    		league = leagueRepository.findById(leagueId).orElseThrow(() -> new ResourceNotFoundException("League", "id", leagueId));
    		
    		scheduleResponse = this.generateSchedule(scheduleRequest.getDateFrom(), customerId, leagueId);
    		
    		listJourneyResponse = scheduleResponse.getListJourney();    		    		    		
    		Date dateFrom = listJourneyResponse.get(0).getListMatch().get(0).getHour();	    		
    		Date dateTo = null;    		
    		
    		for (JourneyResponse dataJourney : listJourneyResponse) {    			    			    			    			    		
    			dateTo = dataJourney.getListMatch().get(dataJourney.getListMatch().size()-1).getHour();    			    		    			
    		}
    		
    		Schedule schedule = new Schedule();
    		
    		schedule.setDateFrom(dateFrom);
    		schedule.setDateTo(dateTo);
    		schedule.setState(scheduleRequest.getState());					
    		schedule.setCustomer(customer);	
    		schedule.setLeague(league);    		            		
    		Journey journey = null;    		
    		Schedule scheduleReturn = this.scheduleRepository.save(schedule);
    		
    		for (JourneyResponse data : listJourneyResponse) {
    			
    			listMatchResponse = data.getListMatch();
    			
    			schedule = scheduleRepository.findById(scheduleReturn.getId()).orElseThrow(() -> new ResourceNotFoundException("Schedule", "id", scheduleReturn.getId()));
    			
    			journey = new Journey();
    			journey.setName(data.getName());
    			journey.setDate(data.getDate());
    			journey.setState(true);
    			journey.setType(data.getType());
    			journey.setSequence(data.getSequence());
    			journey.setSchedule(schedule);
    			
    			Journey journeyReturn = this.journeyRepository.save(journey);
    			
    			Game game = null;
    			
    			for (GameResponse dataGame : listMatchResponse) {    				
    				journey = journeyRepository.findById(journeyReturn.getId()).orElseThrow(() -> new ResourceNotFoundException("Journey", "id", journeyReturn.getId()));		        			        			        			    				
    				game = new Game();
        			game.setTeamA(dataGame.getTeamA());
        			game.setTeamB(dataGame.getTeamB());
        			game.setHour(dataGame.getHour());
        			game.setState(true);
        			game.setJourney(journey);
        			game.setSchedule(schedule);
        			this.gameRepository.save(game);        		
    			}
    			
    		}
    		
        } catch (Exception e) {        	
        	logger.info(e.toString());
            throw new BadRequestException("Sorry! Bad Request");            
        }	
		
        return scheduleResponse;
        
    };
	
}