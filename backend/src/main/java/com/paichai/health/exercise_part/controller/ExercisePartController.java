package com.paichai.health.exercise_part.controller;

import com.paichai.health.exercise_part.dto.ExercisePartResponse;
import com.paichai.health.exercise_part.service.ExercisePartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise-parts")
@RequiredArgsConstructor
public class ExercisePartController {

    private final ExercisePartService exercisePartService;

    @GetMapping
    public List<ExercisePartResponse> getAllExerciseParts() {
        return exercisePartService.getAllParts();
    }
}
