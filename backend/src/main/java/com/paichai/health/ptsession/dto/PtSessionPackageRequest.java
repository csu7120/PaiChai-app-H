package com.paichai.health.ptsession.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PtSessionPackageRequest {
    private Integer trainerId;
    private Integer clientId;
    private int totalCount;
    private LocalDate startDate;
    private LocalDate endDate;
}
