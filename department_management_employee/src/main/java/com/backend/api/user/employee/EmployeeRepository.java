package com.backend.api.user.employee;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	boolean existsByTelephoneAndIdNot(String telephone, Long id);
	
	boolean existsByTelephone(String telephone);
	
	Optional<Employee> findByIdCredentials(Long idCredentials);
	
	@Query("SELECT e FROM Employee e WHERE e.idCredentials IN :idCredentials")
	Page<Employee> findSomeByIdCredentialsSet(PageRequest page, Set<Long> idCredentials);
}
