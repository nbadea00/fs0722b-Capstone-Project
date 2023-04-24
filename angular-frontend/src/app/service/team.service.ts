import { Injectable } from '@angular/core';
import { Team } from '../interface/team.interface';
import { environment } from 'src/environments/environment';
import { Observable, take, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { EmployeeService } from './employee.service';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor(private http: HttpClient, private employeeServie: EmployeeService) { }

  arrTeamLeadId: number[] = [];

  public getTeams(dim:number, page: number): Observable<Team[]>{
    return this.http.get<Team[]>(`${environment.URL}/teams?dim=${dim}&page=${page}`).pipe(
      tap((teams) => {
        teams.forEach((team) =>
          this.arrTeamLeadId.push(team.teamLeadId)
        );
        this.employeeServie
          .getUserInfo(this.arrTeamLeadId)
          .pipe(
            take(1),
            tap((users) => {
              for (let i = 0; i < teams.length; i++)
              teams[i].teamLead = users[i];
            })
          )
          .subscribe();
      })
    );
  }

  public getTeamsBySectionId(sectionId: number): Observable<Team[]>{
    return this.http.get<Team[]>(`${environment.URL}/teams/findBySectionId?sectionId=${sectionId}`)
  }

  public getTeamById(id: number): Observable<Team>{
    return this.http.get<Team>(`${environment.URL}/teams/${id}`)
  }
}
