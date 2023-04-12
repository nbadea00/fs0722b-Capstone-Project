import { Department } from "./department.interface";
import { User } from "./user.interface";

export interface Section {
  id: number;
  name: string;
  sectionManagerId: number;
  department : Department;
  sectionManager: User;
}
