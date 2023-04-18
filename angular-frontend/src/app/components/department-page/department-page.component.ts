import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription, filter, map, switchMap, tap } from 'rxjs';
import { AuthDto } from 'src/app/auth/interface/auth-dto.interface';
import { AuthService } from 'src/app/auth/service/auth.service';
import { Department } from 'src/app/interface/department.interface';
import { Section } from 'src/app/interface/section.interface';
import { User } from 'src/app/interface/user.interface';
import { DepartmentService } from 'src/app/service/department.service';
import { EmployeeService } from 'src/app/service/employee.service';
import { SectionService } from 'src/app/service/section.service';

@Component({
  selector: 'app-department-page',
  templateUrl: './department-page.component.html',
  styleUrls: ['./department-page.component.scss'],
})
export class DepartmentPageComponent implements OnInit {
  constructor(
    private ds: DepartmentService,
    private as: AuthService,
    private sectionService: SectionService,
    private route: ActivatedRoute,
    private employeeService: EmployeeService
  ) {}
  subDep!: Subscription;
  subSec!: Subscription;
  subAuth!: Subscription;
  subSectionManager!: Subscription;

  user!: AuthDto;
  department!: Department;
  sections!: Section[];

  ngOnInit(): void {
    this.subAuth = this.as.user$.subscribe((user) => (this.user = user!));
    this.subDep = this.route.paramMap
      .pipe(
        switchMap((params) => {
          return this.ds.getDepartmentById(Number(params.get('id')));
        })
      )
      .subscribe((department) => {
        this.department = department;
        this.subSec = this.sectionService
          .getSectionsByDepartmentId(this.department.id)
          .subscribe((sections) => {
            this.sections = sections;
          });
      });
  }

  removeSections(event:number[]){
    console.log(event)
  }

  getKeys(): string[] {
    return Object.keys(this.department);
  }

  getKeysLead(){
    return Object.keys(this.department.departmentHead)
  }

  ngOnDestroy(): void {
    this.subDep.unsubscribe();
    this.subAuth.unsubscribe();
    this.subSec.unsubscribe();
  }
}
