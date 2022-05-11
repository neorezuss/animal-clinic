import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, repeat, retry, throwError} from "rxjs";
import { AuthService } from "../services/auth.service";
import { Injectable } from "@angular/core";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const authReq = this.updateHeader(req)

    console.log("token was attached")

    return next.handle(authReq).pipe(
      catchError(err => {
        return this.authService.refreshTokens().subscribe(data => {
          localStorage.setItem('accessToken', data.accessToken);
          localStorage.setItem('refreshToken', data.refreshToken);
          console.log('Tokens were refreshed')
          return next.handle(this.updateHeader(req))
        })
      })
    )
  }

  updateHeader(req: HttpRequest<any>) {
    return req.clone({
      // @ts-ignore
      headers: req.headers.set('Authorization', 'Bearer ' + localStorage.getItem('accessToken')),
    });
  }
}
