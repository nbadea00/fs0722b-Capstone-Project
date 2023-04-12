import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Department } from '../interface/department.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  constructor(private  http: HttpClient) { }

  public getDepartments(): Observable<Department[]>{
    return this.http.get<Department[]>(`${environment.URL}/departments`)
  }

  public deleteDepartment(id:number): Observable<string>{
    return this.http.delete<string>(`${environment.URL}/departments/${id}`)
  }

  public postDepartment(dep:Department): Observable<Department>{
    return this.http.post<Department>(`${environment.URL}/departments`, dep)
  }

  public putDepartment(dep:Department, id:number): Observable<Department>{
    return this.http.put<Department>(`${environment.URL}/departments/${id}`, dep)
  }

  public getDepartmentById(id: number): Observable<Department>{
    return this.http.get<Department>(`${environment.URL}/departments/${id}`)
  }
}
