package com.paichai.health.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.paichai.health.role.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> { 
	Optional<Role> findByRoleId(String roleId);
}
