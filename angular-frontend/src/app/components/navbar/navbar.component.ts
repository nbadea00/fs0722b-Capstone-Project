import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/service/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit, OnDestroy {

  constructor(private auth: AuthService) { }


  sub!: Subscription;
  isloggedIn: boolean = false;
  ngOnInit(): void {
    this.sub = this.auth.isLoggedIn$.subscribe(loggedIn => this.isloggedIn = loggedIn);
  }

  logout(){
    this.auth.logout();
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
