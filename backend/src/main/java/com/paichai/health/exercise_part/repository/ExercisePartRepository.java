package com.paichai.health.exercise_part.repository;

import com.paichai.health.exercise_part.entity.ExercisePart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercisePartRepository extends JpaRepository<ExercisePart, Integer> {
}