package com.paichai.health.trainerclientrequest.service;

import com.paichai.health.trainerclientrequest.dto.TrainerClientRequestRequest;
import com.paichai.health.trainerclientrequest.dto.TrainerClientRequestResponse;
import com.paichai.health.trainerclientrequest.entity.TrainerClientRequest;
import com.paichai.health.trainerclientrequest.repository.TrainerClientRequestRepository;
import com.paichai.health.user.entity.User;
import com.paichai.health.user.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerClientRequestService {

    @Autowired
    private TrainerClientRequestRepository trainerClientRequestRepository;

    @Autowired
    private UserRepository userRepository;

    // 유저가 트레이너에게 pt 요청
    public void sendRequest(TrainerClientRequestRequest requestDto) {
        User trainer = userRepository.findById(requestDto.getTrainerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 트레이너입니다."));
        User client = userRepository.findById(requestDto.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        TrainerClientRequest request = new TrainerClientRequest();
        request.setTrainer(trainer);
        request.setClient(client);
        request.setStatus("PENDING");
        request.setRequestedAt(LocalDateTime.now());

        trainerClientRequestRepository.save(request);
    }
    
    // 트레이너가 요청 허락
    public List<TrainerClientRequestResponse> getPendingRequests(int trainerId) {
        List<TrainerClientRequest> requests =
            trainerClientRequestRepository.findByTrainerUserIdAndStatus(trainerId, "PENDING");

        return requests.stream()
            .map(TrainerClientRequestResponse::new)
            .collect(Collectors.toList());
    }
    
    // 요청 수락·거절 후 status 변경
    @Transactional
    public void updateRequestStatus(int requestId, String status) {
        TrainerClientRequest request = trainerClientRequestRepository.findById(requestId)
            .orElseThrow(() -> new IllegalArgumentException("요청이 존재하지 않습니다."));

        request.setStatus(status);
        request.setRespondedAt(LocalDateTime.now());
    }
    
    // T-C requests 테이블에서 ACCEPTED 상태만 조회
    public List<TrainerClientRequestResponse> getAcceptedClients(int trainerId) {
        List<TrainerClientRequest> requests =
            trainerClientRequestRepository.findByTrainerUserIdAndStatus(trainerId, "ACCEPTED");

        return requests.stream()
            .map(TrainerClientRequestResponse::new)
            .collect(Collectors.toList());
    }
}
