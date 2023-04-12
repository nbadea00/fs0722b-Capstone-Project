import { User } from "./user.interface";

export interface Department {
  id: number;
  name: string;
  departmentHeadId: number;
  departmentHead: User;
}
