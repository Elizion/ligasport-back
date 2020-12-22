package com.core.ligasport.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.core.ligasport.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findById(Long customerId);

}
