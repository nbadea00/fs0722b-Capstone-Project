package com.backend.api.user.role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

	boolean existsByRoleNameAndIdNot(ERole eRole, Long id);
	boolean existsByRoleName(ERole eRole);
	
	public Optional<Role> findByRoleName(ERole eRole);
	
}
