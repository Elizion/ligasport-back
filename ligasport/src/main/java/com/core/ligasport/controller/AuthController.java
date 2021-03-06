package com.core.ligasport.controller;

import com.core.ligasport.model.Role;
import com.core.ligasport.model.RoleName;
import com.core.ligasport.model.User;
import com.core.ligasport.payload.ApiResponse;
import com.core.ligasport.payload.JwtAuthResponse;
import com.core.ligasport.payload.LoginRequest;
import com.core.ligasport.payload.SignUpRequest;
import com.core.ligasport.repository.RoleRepository;
import com.core.ligasport.repository.UserRepository;
import com.core.ligasport.security.JwtTokenProvider;
import com.core.ligasport.util.AppException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
    
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    
    	
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

    	logger.info(jwt);
    	
        return ResponseEntity.ok(new JwtAuthResponse(jwt));
        
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    	
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<Object>(new ApiResponse(false, "¡Este nombre de usuario ya está en uso!"), HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<Object>(new ApiResponse(false, "¡Dirección de correo electrónico ya está en uso!"), HttpStatus.BAD_REQUEST);
        }
                
        User user = new User(
        		signUpRequest.getName(),
        		signUpRequest.getLastname(),         		
                signUpRequest.getEmail(), 
                signUpRequest.getUsername(),
                signUpRequest.getPassword());
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("Rol de usuario no establecido."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);
        
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}").buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Usuario registrado"));
        
    }
    
}
