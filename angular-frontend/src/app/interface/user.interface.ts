import { Role } from "./role.interface";
import { Skill } from "./skill.interface";

export interface User {
  id: number;
  firstname: String;
  lastname: string;
  telephone: string;
  birthday: Date;
  companyRoles:Role[];
  skills:Skill[];
}
