package com.core.ligasport.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.core.ligasport.security.CurrentUser;
import com.core.ligasport.security.UserPrincipal;
import com.core.ligasport.service.TeamService;

@RestController
@RequestMapping("/api/team")
public class TeamController {

	@Autowired
    private TeamService teamService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public void createTeam(@CurrentUser UserPrincipal currentUser, @RequestPart("file") @Valid MultipartFile multipartFile) {
		
		this.teamService.createTeamFromXls(currentUser, multipartFile);			
		
	}
	
}
