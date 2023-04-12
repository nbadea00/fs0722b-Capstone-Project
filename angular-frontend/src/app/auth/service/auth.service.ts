import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AuthDto } from '../interface/auth-dto.interface';
import { LoginDto } from '../interface/login-dto.interface';
import { BehaviorSubject, catchError, map, tap, throwError } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';
import { SingupDto } from '../interface/singup-dto.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private authSubject = new BehaviorSubject<null | AuthDto>(null);

  jwtHelper = new JwtHelperService();

  user$ = this.authSubject.asObservable();
  isLoggedIn$ = this.user$.pipe(map((user) => !!user));
  autoLogoutTimer: any;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.restoreUser();
  }

  public login(data: LoginDto) {
    return this.http.post<AuthDto>(`${environment.URL}/auth/login`, data).pipe(
      tap((data) => {
        localStorage.setItem('utente', JSON.stringify(data));
        const expirationData = this.jwtHelper.getTokenExpirationDate(
          data.accessToken
        ) as Date;

        this.autoLogout(expirationData);

        this.authSubject.next(data);
        this.router.navigate(['/']);
      })
    );
  }

  public signup(data: SingupDto) {
    return this.http
      .post(`${environment.URL}/auth/signup`, data)
      .pipe(catchError(async (err) => this.errors(err)));
  }

  restoreUser() {
    const userJson = localStorage.getItem('utente');
    if (!userJson) {
      return;
    }
    const user: AuthDto = JSON.parse(userJson);
    if (this.jwtHelper.isTokenExpired(user.accessToken)) {
      return;
    }

    this.authSubject.next(user);

    const expirationDate = this.jwtHelper.getTokenExpirationDate(
      user.accessToken
    ) as Date;
    this.autoLogout(expirationDate);
  }

  public autoLogout(expirationData: Date) {
    const expMap = expirationData.getTime() - new Date().getTime();
    this.autoLogoutTimer = setTimeout(() => {
      this.logout();
    }, expMap);
  }

  public logout() {
    this.authSubject.next(null);
    localStorage.removeItem('utente');

    if (this.autoLogoutTimer) {
      clearTimeout(this.autoLogoutTimer);
    }
    this.router.navigate(['/login']);
  }

  private errors(err: any) {
    switch (err.error) {
      case 'Email and password are required':
        throw new Error('Email e password sono obbligatorie');
        break;
      case 'Email already exists':
        return new Error('Utente già registrato');
        break;
      case 'Email format is invalid':
        return new Error('Email scritta male');
        break;
      case 'Cannot find user':
        return new Error("L'utente non esiste");
        break;
      default:
        return new Error(err.error);
        break;
    }
  }
}
