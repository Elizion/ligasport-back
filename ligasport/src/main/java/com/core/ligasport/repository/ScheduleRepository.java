package com.core.ligasport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.core.ligasport.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	Optional<Schedule> findById(Long scheduleId);
	
}
