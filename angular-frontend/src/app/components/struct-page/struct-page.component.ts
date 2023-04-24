import { Component, OnInit, OnDestroy } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ActivatedRoute } from '@angular/router';
import { Subscription, switchMap } from 'rxjs';
import { DepartmentService } from 'src/app/service/department.service';
import { SectionService } from 'src/app/service/section.service';
import { TeamService } from 'src/app/service/team.service';

@Component({
  selector: 'app-struct-page',
  templateUrl: './struct-page.component.html',
  styleUrls: ['./struct-page.component.scss'],
})
export class StructPageComponent implements OnInit, OnDestroy {
  constructor(
    private route: ActivatedRoute,
    private sectionService: SectionService,
    private teamService: TeamService,
    private departmentService: DepartmentService
  ) {}

  ngOnDestroy(): void {
    this.sub.unsubscribe();
    this.subElements.unsubscribe();
  }

  elements: any;
  keys!: string[];
  key!: string;
  type!: string;
  pageSize: number = 5;
  pageIndex: number = 0;
  count: number = 0;
  sub!: Subscription;
  subElements!: Subscription;

  ngOnInit(): void {
    this.sub = this.route.paramMap.subscribe((params) => {
      this.key = params.get('key')!;
      this.getElements(this.key, this.pageSize*2, 0);
    });
  }

  removeTeams(event: any): void {
    console.log(event);
  }

  pageEvent(event: PageEvent): void {
    this.pageSize = event.pageSize;
    this.pageIndex = event.pageIndex;
    if(event.previousPageIndex! > event.pageIndex) this.count++;

    if(event.previousPageIndex! <= event.pageIndex && this.elements.length % event.pageSize === 0 && this.count === 0) this.getElements(this.key, event.pageSize * (event.pageIndex + 2), 0);

    if(event.previousPageIndex! < event.pageIndex && this.count > 0) this.count--;
  }

  getElements(key: string, dim: number, page: number): void {
    switch (key) {
      case 'teams':
        this.subElements = this.teamService
          .getTeams(dim, page)
          .subscribe((teams) => {
            this.elements = teams;
          });
        this.type = 'team';
        this.keys = ['id', 'teamCode', 'teamLead'];
        break;
      case 'sections':
        this.subElements = this.sectionService
          .getSections()
          .subscribe((sections) => (this.elements = sections));
        this.type = 'section';
        this.keys = ['id', 'name', 'sectionManager'];
        break;
      case 'departments':
        this.subElements = this.departmentService
          .getDepartments()
          .subscribe((departments) => (this.elements = departments));
        this.type = 'department';
        this.keys = ['id', 'name', 'departmentHead'];
        break;
    }
  }
}
