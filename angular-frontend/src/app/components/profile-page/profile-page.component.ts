import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription, take, tap } from 'rxjs';
import { AuthService } from 'src/app/auth/service/auth.service';
import { User } from 'src/app/interface/user.interface';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit, OnDestroy {

  constructor(private auth: AuthService, private employeeService: EmployeeService) { }


  sub!:Subscription;
  user!: User;

  edit: boolean = true;

  ngOnInit(): void {
    //this.sub = this.employeeService.getUserInfoByToken().subscribe(userInfo => console.log(userInfo))
  }

  ngOnDestroy(): void {
    //this.sub.unsubscribe();
  }
}
