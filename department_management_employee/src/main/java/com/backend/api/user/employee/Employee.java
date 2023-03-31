package com.backend.api.user.employee;

import java.sql.Date;
import java.util.Set;

import com.backend.api.user.role.Role;
import com.backend.api.user.skill.Skill;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "firstname", nullable = false)
	private String firstname;
	@Column(name = "lastname", nullable = false)
	private String lastname;
	@Column(name = "telephone", nullable = false, unique = true)
	@Size(min = 10, max = 16, message = "Invalid phone number format")
	private String telephone;
	@Column(name = "birthday", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@Column(name = "credentials_id", nullable = false, unique = true)
	private Long idCredentials;
	
	@ManyToMany(cascade = {CascadeType.MERGE})
	private Set<Role> companyRoles;
	
	@ManyToMany(cascade = {CascadeType.MERGE})
	private Set<Skill> skills;
}
