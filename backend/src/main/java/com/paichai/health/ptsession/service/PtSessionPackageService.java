package com.paichai.health.ptsession.service;

import com.paichai.health.ptsession.dto.PtSessionPackageRequest;
import com.paichai.health.ptsession.entity.PtSessionPackage;
import com.paichai.health.ptsession.repository.PtSessionPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PtSessionPackageService {

    private final PtSessionPackageRepository ptSessionPackageRepository;

    // pt 등록
    public void save(PtSessionPackageRequest request) {
        PtSessionPackage entity = PtSessionPackage.builder()
                .trainerId(request.getTrainerId())
                .clientId(request.getClientId())
                .totalCount(request.getTotalCount())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .createdAt(LocalDateTime.now())
                .build();

        ptSessionPackageRepository.save(entity);
    }
    
    // pt 계약 조회
    public boolean isAlreadyRegistered(Integer trainerId, Integer clientId) {
        return ptSessionPackageRepository.findByTrainerIdAndClientId(trainerId, clientId).isPresent();
    }
}
