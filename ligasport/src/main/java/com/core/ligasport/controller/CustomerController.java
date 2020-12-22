package com.core.ligasport.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.core.ligasport.model.Customer;
import com.core.ligasport.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
		
	@Autowired
    private CustomerService customerService;
	
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public Customer createCustomer(
    	@RequestPart("name") @Valid String name,
    	@RequestPart("apellidoPaterno") @Valid String apellidoPaterno,
    	@RequestPart("apellidoMaterno") @Valid String apellidoMaterno,
    	@RequestPart("file") @Valid MultipartFile file,
    	@RequestPart("email") @Valid String email,
    	@RequestPart("cellphone") @Valid String cellphone,
    	@RequestPart("localphone") @Valid String localphone) {    	    	
        			
    	Customer customer = this.customerService.createCustomer(name, apellidoPaterno, apellidoMaterno, file, email, cellphone, localphone);
		
        return customer;
        
    }
    
    @RequestMapping(value = "/update/{customerId}", method = RequestMethod.PUT, consumes = { "multipart/form-data" })
    public Customer updateCustomer(
    	@RequestPart("name") @Valid String name,
    	@RequestPart("apellidoPaterno") @Valid String apellidoPaterno,
    	@RequestPart("apellidoMaterno") @Valid String apellidoMaterno,
    	@RequestPart("file") @Valid MultipartFile file,
    	@RequestPart("email") @Valid String email,
    	@RequestPart("cellphone") @Valid String cellphone,
    	@RequestPart("localphone") @Valid String localphone,
    	@PathVariable Long customerId) {    	    	
        			
    	Customer customer = this.customerService.findById(customerId);
    	
    	customer = this.customerService.updateCustomer(name, apellidoPaterno, apellidoMaterno, file, email, cellphone, localphone, customerId, customer);
		
        return customer;
        
    }
        	
}
