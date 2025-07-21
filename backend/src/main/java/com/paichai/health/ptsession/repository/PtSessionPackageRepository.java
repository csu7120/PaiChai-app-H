package com.paichai.health.ptsession.repository;

import com.paichai.health.ptsession.entity.PtSessionPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PtSessionPackageRepository extends JpaRepository<PtSessionPackage, Integer> {

    // 트레이너와 클라이언트의 계약 정보를 조회
    Optional<PtSessionPackage> findByTrainerIdAndClientId(Integer trainerId, Integer clientId);

    // 클라이언트 기준 조회 (예: MyPage 등)
    Optional<PtSessionPackage> findByClientId(Integer clientId);
}
