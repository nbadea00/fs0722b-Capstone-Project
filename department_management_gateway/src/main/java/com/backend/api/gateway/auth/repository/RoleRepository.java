package com.backend.api.gateway.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.api.gateway.auth.entity.ERole;
import com.backend.api.gateway.auth.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
