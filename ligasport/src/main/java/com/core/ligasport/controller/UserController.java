package com.core.ligasport.controller;

import java.net.URI;

import javax.validation.Valid;

import com.core.ligasport.payload.*;
import com.core.ligasport.model.User;
import com.core.ligasport.repository.RoleRepository;
import com.core.ligasport.security.UserPrincipal;
import com.core.ligasport.service.PollService;
import com.core.ligasport.service.UserService;
import com.core.ligasport.security.CurrentUser;
import com.core.ligasport.util.AppConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PollService pollService;
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
       
    @PostMapping("/user/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {    	
        User user = userService.createUser(userRequest);            	
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}").buildAndExpand(user.getUsername()).toUri();                
    	return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));        
    }
    
    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {    	
    	UserProfile userProfile = userService.getUserProfile(username);    	
    	return userProfile;        
    }
    
    @GetMapping("/user/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {        
    	UserSummary userSummary = userService.getCurrentUser(currentUser);    	
        return userSummary;        
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {    	
        Boolean isAvailable = userService.checkUsernameAvailability(username);        
        return new UserIdentityAvailability(isAvailable);        
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {    	
        Boolean isAvailable = userService.checkEmailAvailability(email);        
        return new UserIdentityAvailability(isAvailable);        
    }
    
    @GetMapping("/users/{username}/polls")
    public PagedResponse<PollResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
                                                         @CurrentUser UserPrincipal currentUser,
                                                         @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getPollsCreatedBy(username, currentUser, page, size);
    }


    @GetMapping("/users/{username}/votes")
    public PagedResponse<PollResponse> getPollsVotedBy(@PathVariable(value = "username") String username,
                                                       @CurrentUser UserPrincipal currentUser,
                                                       @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getPollsVotedBy(username, currentUser, page, size);
    }

}
