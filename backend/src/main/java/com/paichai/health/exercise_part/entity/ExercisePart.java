package com.paichai.health.exercise_part.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exercise_parts")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExercisePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partId;

    @Column(nullable = false)
    private String name;

    private String type;
}
