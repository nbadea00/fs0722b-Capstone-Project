import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable, switchMap, take } from 'rxjs';
import { AuthService } from '../service/auth.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private auth: AuthService) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    return this.auth.user$.pipe(
      take(1),
      switchMap((user) => {
        if (!user) return next.handle(request);

        const newReq = request.clone({
          headers: request.headers.set(
            'Authorization',
            `${user.tokenType} ${user.accessToken}`
          ),
        });

        console.log(newReq);
        return next.handle(newReq);
      })
    );
  }
}
