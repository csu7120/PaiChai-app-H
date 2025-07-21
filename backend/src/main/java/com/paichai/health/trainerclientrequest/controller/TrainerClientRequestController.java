package com.paichai.health.trainerclientrequest.controller;

import com.paichai.health.trainerclientrequest.dto.TrainerClientRequestRequest;
import com.paichai.health.trainerclientrequest.dto.TrainerClientRequestResponse;
import com.paichai.health.trainerclientrequest.dto.TrainerClientRequestStatusUpdateRequest;
import com.paichai.health.trainerclientrequest.service.TrainerClientRequestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainer-request")
public class TrainerClientRequestController {

    @Autowired
    private TrainerClientRequestService trainerClientRequestService;

    @PostMapping
    public ResponseEntity<String> sendTrainerRequest(@RequestBody TrainerClientRequestRequest requestDto) {
        trainerClientRequestService.sendRequest(requestDto);
        return ResponseEntity.ok("요청이 전송되었습니다.");
    }
    
    @GetMapping
    public ResponseEntity<List<TrainerClientRequestResponse>> getTrainerRequests(@RequestParam int trainerId) {
        List<TrainerClientRequestResponse> response = trainerClientRequestService.getPendingRequests(trainerId);
        return ResponseEntity.ok(response);
    }
    
    // 요청 수락·거절 트레이너 매핑
    @PatchMapping("/{requestId}")
    public ResponseEntity<String> updateStatus(
            @PathVariable int requestId,
            @RequestBody TrainerClientRequestStatusUpdateRequest requestDto) {

        trainerClientRequestService.updateRequestStatus(requestId, requestDto.getStatus());
        return ResponseEntity.ok("요청 상태가 업데이트되었습니다.");
    }
    
    // 수락 조회
    @GetMapping("/clients")
    public ResponseEntity<List<TrainerClientRequestResponse>> getAcceptedClients(@RequestParam int trainerId) {
        List<TrainerClientRequestResponse> response = trainerClientRequestService.getAcceptedClients(trainerId);
        return ResponseEntity.ok(response);
    }
    
    
}
