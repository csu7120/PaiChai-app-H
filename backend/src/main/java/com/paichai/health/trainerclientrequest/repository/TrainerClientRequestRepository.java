package com.paichai.health.trainerclientrequest.repository;

import com.paichai.health.trainerclientrequest.entity.TrainerClientRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerClientRequestRepository extends JpaRepository<TrainerClientRequest, Integer> {
	
	// SELECT * FROM trainer_client_requests WHERE trainer_id = ? AND status = ?
	List<TrainerClientRequest> findByTrainerUserIdAndStatus(int trainerId, String status);
	
}