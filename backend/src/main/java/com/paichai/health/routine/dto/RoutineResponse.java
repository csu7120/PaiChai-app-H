package com.paichai.health.routine.dto;

import com.paichai.health.routine.entity.Routine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class RoutineResponse {

    private Integer routineId;
    private String title;
    private String description;
    private LocalDateTime createdAt;

    private Integer authorId; // 🔽 추가

    public static RoutineResponse fromEntity(Routine routine) {
        return RoutineResponse.builder()
            .routineId(routine.getRoutineId())
            .title(routine.getTitle())
            .description(routine.getDescription())
            .createdAt(routine.getCreatedAt())
            .authorId(routine.getAuthor().getUserId()) // 🔽 User → userId 추출
            .build();
    }
}
