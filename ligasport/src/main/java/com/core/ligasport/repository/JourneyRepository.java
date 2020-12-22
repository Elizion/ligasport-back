package com.core.ligasport.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.core.ligasport.model.Journey;

public interface JourneyRepository extends JpaRepository<Journey, Long> {

	Optional<Journey> findById(Long journeyId);
	
}
