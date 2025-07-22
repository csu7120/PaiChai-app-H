package com.paichai.health.routine_exercise_part.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "routine_exercise_parts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineExercisePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer routineId;

    private Integer partId;
}
