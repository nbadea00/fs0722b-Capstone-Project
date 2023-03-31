package com.backend.api.project.project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String projectName;
	@Column(name = "description")
	private String projectDescription;
	@Column(name = "team_id")
	private Long teamId;
	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private ProjectState projectState;
	
	@ManyToOne
	@JoinColumn(name = "main_project_id")
	private Project mainProjectId;
}
