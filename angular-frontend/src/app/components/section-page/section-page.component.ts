import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription, combineLatest, switchMap } from 'rxjs';
import { AuthDto } from 'src/app/auth/interface/auth-dto.interface';
import { AuthService } from 'src/app/auth/service/auth.service';
import { Section } from 'src/app/interface/section.interface';
import { Team } from 'src/app/interface/team.interface';
import { User } from 'src/app/interface/user.interface';
import { EmployeeService } from 'src/app/service/employee.service';
import { SectionService } from 'src/app/service/section.service';
import { TeamService } from 'src/app/service/team.service';

@Component({
  selector: 'app-section-page',
  templateUrl: './section-page.component.html',
  styleUrls: ['./section-page.component.scss'],
})
export class SectionPageComponent implements OnInit {
  constructor(
    private as: AuthService,
    private ts: TeamService,
    private employeeService: EmployeeService,
    private sectionService: SectionService,
    private route: ActivatedRoute
  ) {}

  subSec!: Subscription;
  subTeam!: Subscription;
  subAuth!: Subscription;
  subTeamLead!: Subscription;

  user!: AuthDto;
  teams!: Team[];
  section!: Section;

  ngOnInit(): void {
    this.subAuth = this.as.user$.subscribe((user) => (this.user = user!));
    this.subSec = this.route.paramMap
      .pipe(
        switchMap((params) => {
          return this.sectionService.getSectionById(Number(params.get('id')));
        })
      )
      .subscribe((section) => {
        this.section = section;
        this.subTeam = this.ts
          .getTeamsBySectionId(this.section.id)
          .subscribe((teams) => {
            this.teams = teams;
            let arr: number[] = [];
            this.teams.forEach((team) => arr.push(team.teamLeadId));
            this.subTeamLead = this.employeeService
              .getUserInfo(arr)
              .subscribe((user) => {
                for (let i = 0; i < this.teams.length; i++) {
                  this.teams[i].teamLead = user[i];
                }
              });
          });
      });
  }

  removeTeams(event: number[]):void {
    console.log(event);
  }

  ngOnDestroy(): void {
    this.subSec.unsubscribe();
    this.subTeam.unsubscribe();
    this.subAuth.unsubscribe();
    this.subTeamLead.unsubscribe();
  }
}
