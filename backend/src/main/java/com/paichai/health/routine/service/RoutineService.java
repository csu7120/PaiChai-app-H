package com.paichai.health.routine.service;

import com.paichai.health.routine.dto.RoutineRequest;
import com.paichai.health.routine.dto.RoutineResponse;
import com.paichai.health.routine.entity.Routine;
import com.paichai.health.routine.repository.RoutineRepository;
import com.paichai.health.routine_exercise_part.entity.RoutineExercisePart;
import com.paichai.health.routine_exercise_part.repository.RoutineExercisePartRepository;
import com.paichai.health.user.entity.User;
import com.paichai.health.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final RoutineExercisePartRepository routineExercisePartRepository;
    private final UserRepository userRepository; // ✅ 추가

    public RoutineResponse createRoutine(Integer authorId, RoutineRequest request) {

        // ✅ authorId로 User 객체 조회
        User user = userRepository.findById(authorId)
            .orElseThrow(() -> new RuntimeException("❌ 사용자 정보 없음"));

        // ✅ 루틴 저장
        Routine routine = Routine.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .author(user) // ✅ User 객체 주입
            .build();

        routine = routineRepository.save(routine);

        // ✅ 선택된 운동부위 저장
        List<Integer> partIds = request.getExercisePartIds();

        if (partIds != null && !partIds.isEmpty()) {
            for (Integer partId : partIds) {
                RoutineExercisePart mapping = RoutineExercisePart.builder()
                        .routineId(routine.getRoutineId())
                        .partId(partId)
                        .build();
                routineExercisePartRepository.save(mapping);
            }
        }

        return RoutineResponse.fromEntity(routine);
    }
}
