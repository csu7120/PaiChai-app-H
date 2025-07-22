package com.paichai.health.exercise_part.service;

import com.paichai.health.exercise_part.dto.ExercisePartResponse;
import com.paichai.health.exercise_part.repository.ExercisePartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExercisePartService {

    private final ExercisePartRepository exercisePartRepository;

    public List<ExercisePartResponse> getAllParts() {
        return exercisePartRepository.findAll()
                .stream()
                .map(ExercisePartResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
