import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Department } from '../interface/department.interface';
import { BehaviorSubject, Observable, take, tap } from 'rxjs';
import { EmployeeService } from './employee.service';

@Injectable({
  providedIn: 'root',
})
export class DepartmentService {
  constructor(
    private http: HttpClient,
    private employeeServie: EmployeeService
  ) {}
  arrDepartmentHeadId: number[] = [];

  public getDepartments(): Observable<Department[]> {
    return this.http.get<Department[]>(`${environment.URL}/departments`).pipe(
      tap((departments) => {
        departments.forEach((department) =>
          this.arrDepartmentHeadId.push(department.departmentHeadId)
        );
        this.employeeServie
          .getUserInfo(this.arrDepartmentHeadId)
          .pipe(
            take(1),
            tap((users) => {
              for (let i = 0; i < departments.length; i++)
                departments[i].departmentHead = users[i];
            })
          )
          .subscribe();
      })
    );
  }

  public deleteDepartment(id: number): Observable<string> {
    return this.http.delete<string>(`${environment.URL}/departments/${id}`);
  }

  public postDepartment(dep: Department): Observable<Department> {
    return this.http.post<Department>(`${environment.URL}/departments`, dep);
  }

  public putDepartment(dep: Department, id: number): Observable<Department> {
    return this.http.put<Department>(
      `${environment.URL}/departments/${id}`,
      dep
    );
  }

  public getDepartmentById(id: number): Observable<Department> {
    return this.http
      .get<Department>(`${environment.URL}/departments/${id}`)
      .pipe(
        tap((department) =>
          this.employeeServie
            .getUserInfo([department.departmentHeadId])
            .pipe(
              take(1),
              tap(
                (departmentHead) =>
                  (department.departmentHead = departmentHead[0])
              )
            )
            .subscribe()
        )
      );
  }
}
