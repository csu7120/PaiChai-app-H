package com.paichai.healthhelper.ptsession.model;

public class PtSessionPackageRequest {
    private int trainerId;
    private int clientId;
    private int totalCount;
    private String startDate;
    private String endDate;

    public PtSessionPackageRequest(int trainerId, int clientId, int totalCount, String startDate, String endDate) {
        this.trainerId = trainerId;
        this.clientId = clientId;
        this.totalCount = totalCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
