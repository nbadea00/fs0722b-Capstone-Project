package com.backend.api.user.employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	boolean existsByTelephone(String telephone);
	Optional<Employee> findByIdCredentials(Long idCredentials);
}
