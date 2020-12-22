package com.core.ligasport.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.reader.ReaderConfig;
import net.sf.jxls.reader.XLSReadMessage;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.ligasport.model.Customer;
import com.core.ligasport.model.League;
import com.core.ligasport.model.Team;
import com.core.ligasport.payload.XlsTeamDataRequest;
import com.core.ligasport.payload.XlsTeamRequest;
import com.core.ligasport.repository.CustomerRepository;
import com.core.ligasport.repository.LeagueRepository;
import com.core.ligasport.repository.TeamRepository;
import com.core.ligasport.security.UserPrincipal;
import com.core.ligasport.util.BadRequestException;
import com.core.ligasport.util.JxlsDateConverter;
import com.core.ligasport.util.JxlsReaderBuilderTeam;
import com.core.ligasport.util.ReadFileFromStream;
import com.core.ligasport.util.ResourceNotFoundException;

@Service
public class TeamService {
	
    @Autowired
    private TeamRepository teamRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private LeagueRepository leagueRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);	
	
    public List<XlsTeamRequest> createTeamFromXls(UserPrincipal currentUser, MultipartFile multipartFile) {    	
		byte[] xlsBytes;
		List<XlsTeamRequest> loadedData = null;
		List<XLSReadMessage> readMessages = null;
		XSSFWorkbook workbook = null; 
		List<String> sheetNames = null; 		  				
		try {			
			xlsBytes 	 = ReadFileFromStream.readFileFromStream(multipartFile.getInputStream());
			readMessages = new ArrayList<XLSReadMessage>();
		    workbook 	 = new XSSFWorkbook(multipartFile.getInputStream());	    	    
		    sheetNames 	 = new ArrayList<String>();		    
			for(int i=0; i<workbook.getNumberOfSheets(); i++) {
			    sheetNames.add(workbook.getSheetName(i));
			}			
			Map<String, XlsTeamRequest> beans = new HashMap<String, XlsTeamRequest>();						
			XLSReader mainReader = JxlsReaderBuilderTeam.build(sheetNames, beans);						
			List<String> beanKeys = new ArrayList<String>(beans.keySet());						
			ReaderConfig.getInstance().setSkipErrors(true);			
			ConvertUtils.register(new JxlsDateConverter(), java.util.Date.class);						
			XLSReadStatus readStatus = mainReader.read(new ByteArrayInputStream(xlsBytes), beans);						
			for (Object message : readStatus.getReadMessages()) {
				readMessages.add((XLSReadMessage)message);
			}			
			loadedData = new ArrayList<XlsTeamRequest>();			
			for (String key : beanKeys) {
				Object obj = beans.get(key);
				loadedData.add((XlsTeamRequest)obj);
			}			
			if(loadedData.size() > 0) {
				this.processModelTeamRequest(loadedData);
			}		
		} catch (Exception e) {
			e.printStackTrace();			
		}		
		return loadedData;		
    }
    	
    public List<XlsTeamRequest> processModelTeamRequest(List<XlsTeamRequest> loadedData) {    	
    	Long customerId = null;
    	Long leagueId = null;
    	String[] uniforms = null;
    	String gender = null;    	    	    
    	String name = null;
    	Integer sequence = null;
    	Date dateFoundation = null;
    	Boolean state = null;    	
    	if(loadedData.size() > 0 ) {    		
    		for (XlsTeamRequest data : loadedData) {				    										
    			customerId = data.getCustomerId();
    			leagueId = data.getLeagueId();				
    			gender = data.getGender();    			
    			List<XlsTeamDataRequest> rowValues = data.getXlsTeamDataRequest();				    			
    			for (XlsTeamDataRequest dataValue : rowValues) {    				
    				dataValue.setState(true);    				
    				name = dataValue.getName();
    				sequence = dataValue.getSequence();
    				dateFoundation = dataValue.getDateFoundation();
    				state = dataValue.getState();
    				uniforms = dataValue.getUniforms();    				
    				this.insertTeam(customerId, leagueId, uniforms, gender, name, sequence, dateFoundation, state);    				
    			}    			
    		}    		
    	}				
		return loadedData;    
    }
    
    public void insertTeam(Long customerId, Long leagueId, String uniforms[], String gender, String name, Integer sequence, Date dateFoundation, Boolean state) {    	
		Team team = null;								
		try {
			Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
			League league = leagueRepository.findById(leagueId).orElseThrow(() -> new ResourceNotFoundException("League", "id", leagueId));			
			team = new Team();
			team.setGender(gender);
			team.setSequence(sequence);
			team.setName(name);
			team.setDateFoundation(dateFoundation);
			team.setColorUniformA(uniforms[0]);
			team.setColorUniformB(uniforms[1]);
			team.setState(state);
			team.setCustomer(customer);
			team.setLeague(league);			
			team = this.teamRepository.save(team);		
        } catch (Exception e) {        	
        	logger.info(e.toString());
            throw new BadRequestException("Sorry! Bad Request");            
        }			
    }

}
