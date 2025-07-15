package com.paichai.healthhelper.trainerclientrequest.model;

public class TrainerClientRequestStatusUpdateRequest {
    private String status;

    public TrainerClientRequestStatusUpdateRequest(String status) {
        this.status = status;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}