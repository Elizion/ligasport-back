package com.core.ligasport.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.ligasport.model.Customer;
import com.core.ligasport.payload.CustomerRequest;
import com.core.ligasport.repository.CustomerRepository;
import com.core.ligasport.util.BadRequestException;
import com.core.ligasport.util.ReadFileFromStream;
import com.core.ligasport.util.ResourceNotFoundException;

@Service
public class CustomerService {
	
	@Autowired
    private CustomerRepository customerRepository;   
    
    public Customer createCustomer(String name, String apellidoPaterno, String apellidoMaterno, MultipartFile file, String email, String cellphone, String localphone) {
    	    	
    	byte[] xlsBytes = null;        
        String encodedString = null;
        CustomerRequest customerRequest = null;
        Customer customer = null; 
        		
    	try {
    		
			xlsBytes = ReadFileFromStream.readFileFromStream(file.getInputStream());
			encodedString = Base64.getEncoder().encodeToString(xlsBytes);
			
			customerRequest = new CustomerRequest();
			
			customerRequest.setName(name);
			customerRequest.setApellidoPaterno(apellidoPaterno);
			customerRequest.setApellidoMaterno(apellidoMaterno);
			customerRequest.setAvatar(encodedString);	
			customerRequest.setEmail(email);
			customerRequest.setCellphone(cellphone);
			customerRequest.setLocalphone(localphone);
			
			customer = new Customer();
	    	
	    	customer.setName(customerRequest.getName());				
	    	customer.setApellidoPaterno(customerRequest.getApellidoPaterno());
	    	customer.setApellidoMaterno(customerRequest.getApellidoMaterno());
	    	customer.setAvatar(customerRequest.getAvatar());
	    	customer.setEmail(customerRequest.getEmail());
	    	customer.setCellphone(customerRequest.getCellphone());
	    	customer.setLocalphone(customerRequest.getLocalphone());
	    	
	    	customerRepository.save(customer);
	    	
		} catch (IOException e) {
			
            throw new BadRequestException("Bad Request");
            
		} catch (Exception e) {
			
			throw new BadRequestException("Bad Request");
			
		}
		
        return customer;
        
    }
    
    public Customer findById(Long customerId) {
    	Customer customer = null;
    	customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
    	return customer;
    }
    		
    public Customer updateCustomer(String name, String apellidoPaterno, String apellidoMaterno, MultipartFile file, String email, String cellphone, String localphone, Long customerId, Customer customer) {
        	    	
    	byte[] xlsBytes = null;        
        String encodedString = null;
        CustomerRequest customerRequest = null;
            		
    	try {
    		
			xlsBytes = ReadFileFromStream.readFileFromStream(file.getInputStream());
			encodedString = Base64.getEncoder().encodeToString(xlsBytes);
			
			customerRequest = new CustomerRequest();
			
			customerRequest.setName(name);
			customerRequest.setApellidoPaterno(apellidoPaterno);
			customerRequest.setApellidoMaterno(apellidoMaterno);
			customerRequest.setAvatar(encodedString);	
			customerRequest.setEmail(email);
			customerRequest.setCellphone(cellphone);
			customerRequest.setLocalphone(localphone);
			
			customer = new Customer();
	    	
			customer.setId(customerId);
	    	customer.setName(customerRequest.getName());				
	    	customer.setApellidoPaterno(customerRequest.getApellidoPaterno());
	    	customer.setApellidoMaterno(customerRequest.getApellidoMaterno());
	    	customer.setAvatar(customerRequest.getAvatar());
	    	customer.setEmail(customerRequest.getEmail());
	    	customer.setCellphone(customerRequest.getCellphone());
	    	customer.setLocalphone(customerRequest.getLocalphone());
	    	
	    	customerRepository.save(customer);
	    	
		} catch (IOException e) {
			
            throw new BadRequestException("Bad Request");
            
		} catch (Exception e) {
			
			throw new BadRequestException("Bad Request");
			
		}
		
        return customer;
        
    }
        
}
