package com.backend.api.gateway.project.api.task;

import com.backend.api.gateway.project.api.project.ProjectDto;

import lombok.Data;

@Data
public class TaskDto {
	private Long id;
	private String taskDescription;
	private Long userId;
	private TaskState taskState;
	private ProjectDto project;
}
