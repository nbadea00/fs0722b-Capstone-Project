import { Section } from "./section.interface";
import { User } from "./user.interface";

export interface Team {
  id: number;
  teamCode: string;
  teamLeadId: number;
  section: Section;
  mainTeam: Team;
  teamLead: User;
}
