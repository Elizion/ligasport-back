package com.core.ligasport.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.core.ligasport.controller.UserController;
import com.core.ligasport.model.Role;
import com.core.ligasport.model.RoleName;
import com.core.ligasport.model.User;
import com.core.ligasport.payload.ApiResponse;
import com.core.ligasport.payload.UserProfile;
import com.core.ligasport.payload.UserRequest;
import com.core.ligasport.payload.UserSummary;
import com.core.ligasport.repository.PollRepository;
import com.core.ligasport.repository.RoleRepository;
import com.core.ligasport.repository.UserRepository;
import com.core.ligasport.repository.VoteRepository;
import com.core.ligasport.security.UserPrincipal;
import com.core.ligasport.util.AppException;
import com.core.ligasport.util.ResourceNotFoundException;

@Service
public class UserService {
	
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    public User createUser(UserRequest userRequest) {
    	
        if(userRepository.existsByUsername(userRequest.getUsername())) {
            new ResponseEntity<Object>(new ApiResponse(false, "¡Este nombre de usuario ya está en uso!"), HttpStatus.BAD_REQUEST);
        }
        
        if(userRepository.existsByEmail(userRequest.getEmail())) {
            new ResponseEntity<Object>(new ApiResponse(false, "¡Dirección de correo electrónico ya está en uso!"), HttpStatus.BAD_REQUEST);
        }
        
        User user = new User(
        		userRequest.getName(),
        		userRequest.getLastname(),        		
        		userRequest.getEmail(), 
        		userRequest.getUsername(),        		
        		userRequest.getPassword());        
        user.setPassword(passwordEncoder.encode(user.getUsername())); 
        
        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new AppException("User Role not set."));
        
        logger.info(userRole.getName().toString());    
        
        user.setRoles(Collections.singleton(userRole));        
        User save = userRepository.save(user);                
        return save;
        
    }
        
    public UserProfile getUserProfile(String username) {    	
    	User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        long pollCount = pollRepository.countByCreatedBy(user.getId());
        long voteCount = voteRepository.countByUserId(user.getId());        
        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), pollCount, voteCount);        
        return userProfile;        
    }
    
    public UserSummary getCurrentUser(UserPrincipal currentUser) {    	
    	UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getName());    	
        return userSummary;        
    }
    
    public Boolean checkUsernameAvailability(String username) {
    	Boolean isAvailable = !userRepository.existsByUsername(username);
    	return isAvailable;
    }
    
    public Boolean checkEmailAvailability(String email) {
    	Boolean isAvailable = !userRepository.existsByEmail(email);
    	return isAvailable;
    }
    
}
