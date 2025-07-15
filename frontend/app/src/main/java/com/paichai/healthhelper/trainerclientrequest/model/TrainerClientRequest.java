package com.paichai.healthhelper.trainerclientrequest.model;

public class TrainerClientRequest {
    private int requestId;
    private int clientId;
    private String clientName;
    private String clientProfileUrl;
    private String status;
    private String requestedAt;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientProfileUrl() {
        return clientProfileUrl;
    }

    public void setClientProfileUrl(String clientProfileUrl) {
        this.clientProfileUrl = clientProfileUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(String requestedAt) {
        this.requestedAt = requestedAt;
    }
}
