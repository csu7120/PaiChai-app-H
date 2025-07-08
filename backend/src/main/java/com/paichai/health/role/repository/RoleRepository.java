package com.paichai.health.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paichai.health.role.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> { }
