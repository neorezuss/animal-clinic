import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, repeat, retry, throwError} from "rxjs";
import { AuthService } from "../services/auth.service";
import { Injectable } from "@angular/core";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const authReq = req.clone({
      // @ts-ignore
      headers: req.headers.set('Authorization', 'Bearer ' + localStorage.getItem('accessToken')),
    })

    console.log('header added: ' + localStorage.getItem('accessToken'))

    return next.handle(authReq).pipe(
      catchError(err => {
        this.authService.refreshTokens()
        return throwError(err)
      })
    )
  }
}
