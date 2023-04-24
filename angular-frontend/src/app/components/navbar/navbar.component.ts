import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription, map } from 'rxjs';
import { AuthService } from 'src/app/auth/service/auth.service';
import { FlexLayoutService } from 'src/app/service/flex-layout.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit, OnDestroy {

  constructor(private auth: AuthService, private flex: FlexLayoutService) { }

  toggleMenu = false;

  sub!: Subscription;
  subUser!: Subscription;
  isloggedIn: boolean = false;
  isAdmin: boolean = false;
  ngOnInit(): void {
    this.sub = this.auth.isLoggedIn$.subscribe(loggedIn => this.isloggedIn = loggedIn);
    this.subUser = this.auth.user$.subscribe(user => user?.rolesName.includes('ROLE_ADMIN')? this.isAdmin = true: this.isAdmin = false);
  }

  toggle(){
   this.toggleMenu = !this.toggleMenu;
   console.log(this.toggleMenu)
  }

  flexControler(mapArgs: object):string {
    return this.flex.flex(new Map<string, string>(Object.entries(mapArgs)));
  }

  logout(){
    this.auth.logout();
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
    this.subUser.unsubscribe();
  }

}
