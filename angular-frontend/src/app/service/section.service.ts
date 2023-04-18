import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Section } from '../interface/section.interface';
import { Observable, take, tap } from 'rxjs';
import { EmployeeService } from './employee.service';

@Injectable({
  providedIn: 'root',
})
export class SectionService {
  constructor(
    private http: HttpClient,
    private employeeServie: EmployeeService
  ) {}
  arrSectionManagerId: number[] = [];

  public getSections(): Observable<Section[]> {
    return this.http.get<Section[]>(`${environment.URL}/sections`).pipe(
      tap((sections) => {
        sections.forEach((section) =>
          this.arrSectionManagerId.push(section.sectionManagerId)
        );
        this.employeeServie
          .getUserInfo(this.arrSectionManagerId)
          .pipe(
            take(1),
            tap((users) => {
              for (let i = 0; i < sections.length; i++)
                sections[i].sectionManager = users[i];
            })
          )
          .subscribe();
      })
    );
  }

  public getSectionsByDepartmentId(
    departmentId: number
  ): Observable<Section[]> {
    return this.http
      .get<Section[]>(
        `${environment.URL}/sections/findByDepartmentId?departmentId=${departmentId}`
      )
      .pipe(
        tap((sections) => {
          sections.forEach((section) =>
            this.arrSectionManagerId.push(section.sectionManagerId)
          );
          this.employeeServie
            .getUserInfo(this.arrSectionManagerId)
            .pipe(
              take(1),
              tap((users) => {
                for (let i = 0; i < sections.length; i++)
                  sections[i].sectionManager = users[i];
              })
            )
            .subscribe();
        })
      );
  }

  public getSectionById(id: number): Observable<Section> {
    return this.http.get<Section>(`${environment.URL}/sections/${id}`).pipe(
      tap((section) =>
        this.employeeServie
          .getUserInfo([section.sectionManagerId])
          .pipe(
            take(1),
            tap(
              (sectionManager) => (section.sectionManager = sectionManager[0])
            )
          )
          .subscribe()
      )
    );
  }
}
