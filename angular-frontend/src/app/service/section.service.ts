import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Section } from '../interface/section.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SectionService {

  constructor(private  http: HttpClient) { }

  public getSections(): Observable<Section[]>{
    return this.http.get<Section[]>(`${environment.URL}/sections`)
  }

  public getSectionsByDepartmentId(departmentId: number): Observable<Section[]>{
    return this.http.get<Section[]>(`${environment.URL}/sections/findByDepartmentId?departmentId=${departmentId}`)
  }

  public getSectionById(id: number): Observable<Section>{
    return this.http.get<Section>(`${environment.URL}/sections/${id}`)
  }
}
