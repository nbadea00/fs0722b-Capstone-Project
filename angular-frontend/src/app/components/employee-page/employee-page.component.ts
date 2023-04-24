import { Component, OnInit, OnDestroy } from '@angular/core';
import { Scroll } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/interface/user.interface';
import { EmployeeService } from 'src/app/service/employee.service';
import { FlexLayoutService } from 'src/app/service/flex-layout.service';

@Component({
  selector: 'app-employee-page',
  templateUrl: './employee-page.component.html',
  styleUrls: ['./employee-page.component.scss']
})
export class EmployeePageComponent implements OnInit, OnDestroy {

  constructor(private employeeService: EmployeeService, private flex: FlexLayoutService) { }
  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  sub!: Subscription;
  employees!: User[];

  dim: number = this.flex.flex<number>(new Map([['tl', 20],['ld', 20], ['sd', 15], ['default', 10]]));
  page: number = 0;

  onScroll(event:Event){
    if(Math.abs((-window.scrollY + document.body.scrollHeight) / document.body.scrollHeight) % 0.2 <= 0.1) {
      this.dim += 5;
      this.getEmployees()
    };
  }

  ngOnInit(): void {
    this.getEmployees()
    document.addEventListener('scroll', (e:Event) => this.onScroll(e));
  }

  getEmployees(){
    this.sub = this.employeeService.getEmployees(this.dim, this.page).subscribe(employess => this.employees = employess);
  }

}
