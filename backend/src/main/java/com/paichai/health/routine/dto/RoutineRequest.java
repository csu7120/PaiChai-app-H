package com.paichai.health.routine.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineRequest {
    private String title;
    private String description;
    private List<Integer> exercisePartIds;  // ✅ 추가: 운동부위 ID 리스트
}
