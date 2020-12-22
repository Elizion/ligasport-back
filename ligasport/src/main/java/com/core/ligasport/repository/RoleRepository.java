package com.core.ligasport.repository;

import com.core.ligasport.model.Role;
import com.core.ligasport.model.RoleName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
    Optional<Role> findByName(RoleName roleName);
    
}
