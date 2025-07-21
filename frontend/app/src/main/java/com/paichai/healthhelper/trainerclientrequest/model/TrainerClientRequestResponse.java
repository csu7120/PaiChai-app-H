package com.paichai.healthhelper.trainerclientrequest.model;

import com.google.gson.annotations.SerializedName;

public class TrainerClientRequestResponse {
    @SerializedName("requestId")
    private int requestId;

    @SerializedName("clientId")
    private int clientId;

    @SerializedName("clientName")
    private String clientName;

    @SerializedName("clientProfileUrl")
    private String clientProfileUrl;

    @SerializedName("status")
    private String status;

    @SerializedName("requestedAt")
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
