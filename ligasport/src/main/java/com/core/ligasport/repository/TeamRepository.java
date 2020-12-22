package com.core.ligasport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.core.ligasport.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

	Optional<Team> findById(Long teamId);

	@Query(value="SELECT COUNT(sequence) AS total "
			   + "FROM teams "
			   + "WHERE customer_id = ? "
			   + "AND league_id = ? "
			   + "AND state = 1", 
			   nativeQuery = true)
    Integer countTeamsTrue(@Param("customerId") Long customerId, @Param("leagueId") Long leagueId);
	
}

