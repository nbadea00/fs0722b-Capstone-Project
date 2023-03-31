package com.backend.api.gateway.project.api.project;

import lombok.Data;

@Data
public class ProjectDto {
	private Long id;
	private String projectName;
	private String projectDescription;
	private Long teamId;
	private ProjectState projectState;
	private ProjectDto mainProjectId;
}
