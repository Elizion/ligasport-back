package com.core.ligasport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.ligasport.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

	Optional<Game> findById(Long gameId);
	
}
