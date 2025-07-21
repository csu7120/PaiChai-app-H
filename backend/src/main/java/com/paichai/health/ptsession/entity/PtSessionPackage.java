package com.paichai.health.ptsession.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pt_session_packages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PtSessionPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer packageId;  // PK

    @Column(name = "trainer_id", nullable = false)
    private Integer trainerId;  // User.userId

    @Column(name = "client_id", nullable = false)
    private Integer clientId;   // User.userId

    @Column(name = "total_count", nullable = false)
    private int totalCount; // 총 수업 횟수

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // 생성 시간 자동 저장
}
