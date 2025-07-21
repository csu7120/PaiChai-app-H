package com.paichai.health.trainerclientrequest.dto;

import lombok.Data;

@Data
public class TrainerClientRequestStatusUpdateRequest { // 요청 수락·거절 후 업데이트
	private String status; // ACCEPTED or REJECTED
	
}

