package com.core.ligasport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.ligasport.model.League;

public interface LeagueRepository extends JpaRepository<League, Long> {

	Optional<League> findById(Long leagueId);
	
}
