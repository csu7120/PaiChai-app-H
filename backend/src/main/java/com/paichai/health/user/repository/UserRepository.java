package com.paichai.health.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paichai.health.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}