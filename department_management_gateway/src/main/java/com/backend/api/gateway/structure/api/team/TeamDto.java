package com.backend.api.gateway.structure.api.team;

import com.backend.api.gateway.structure.api.section.SectionDto;

import lombok.Data;

@Data
public class TeamDto {
	private Long id;
	private String teamCode;
	private Long teamLeadId;
	private SectionDto section;
	private TeamDto mainTeam;
}
