import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthDto } from 'src/app/auth/interface/auth-dto.interface';
import { AuthService } from 'src/app/auth/service/auth.service';
import { Department } from 'src/app/interface/department.interface';
import { User } from 'src/app/interface/user.interface';
import { DepartmentService } from 'src/app/service/department.service';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  constructor(
    private as: AuthService,
    private ds: DepartmentService,
    private employeeService: EmployeeService
  ) {}
  subAuth!: Subscription;
  subDep!: Subscription;
  subDepartmentHead!: Subscription;

  user!: AuthDto;
  departments!: Department[];

  ngOnInit(): void {
    this.subAuth = this.as.user$.subscribe((user) => (this.user = user!));
    this.subDep = this.ds.getDepartments().subscribe((departments) => {
      this.departments = departments;
      console.log(departments)
    });
  }

  removeDepartments(event:number[]){
    console.log(event)
  }

  ngOnDestroy(): void {
    this.subAuth.unsubscribe();
    this.subDep.unsubscribe();
  }
}
