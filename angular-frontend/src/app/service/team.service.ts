import { Injectable } from '@angular/core';
import { Team } from '../interface/team.interface';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor(private http: HttpClient) { }

  public getTeams(): Observable<Team[]>{
    return this.http.get<Team[]>(`${environment.URL}/teams`)
  }

  public getTeamsBySectionId(sectionId: number): Observable<Team[]>{
    return this.http.get<Team[]>(`${environment.URL}/teams/findBySectionId?sectionId=${sectionId}`)
  }

  public getTeamById(id: number): Observable<Team>{
    return this.http.get<Team>(`${environment.URL}/teams/${id}`)
  }
}
