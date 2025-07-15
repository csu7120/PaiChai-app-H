package com.paichai.health.trainerclientrequest.dto;

import com.paichai.health.trainerclientrequest.entity.TrainerClientRequest;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TrainerClientRequestResponse {
    private int requestId;
    private int clientId;
    private String clientName;
    private String clientProfileUrl;
    private String status;
    private LocalDateTime requestedAt;

    // 생성자
    public TrainerClientRequestResponse(TrainerClientRequest entity) {
        this.requestId = entity.getId();
        this.clientId = entity.getClient().getUserId();
        this.clientName = entity.getClient().getName();
        this.clientProfileUrl = entity.getClient().getProfileUrl();
        this.status = entity.getStatus();
        this.requestedAt = entity.getRequestedAt();
    }
}
