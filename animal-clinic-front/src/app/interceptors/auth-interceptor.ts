import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, mergeMap, Observable, throwError} from "rxjs";
import {AuthService} from "../services/auth.service";
import {Injectable} from "@angular/core";
import {AuthResponse} from "../classes/auth-response";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const authReq = this.updateHeader(req)

    return next.handle(authReq).pipe(
      catchError((err: HttpErrorResponse) => {
        if (err.status === 401 || err.status === 403) {
          return this.authService.refreshTokens()
            .pipe(
              mergeMap((authResponse: AuthResponse) => {
                localStorage.setItem('accessToken', authResponse.accessToken)
                localStorage.setItem('refreshToken', authResponse.refreshToken)

                return next.handle(this.updateHeader(authReq))
              })
            )
        }
        return throwError(err)
      })
    )
  }

  updateHeader(req: HttpRequest<any>) {
    return req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + localStorage.getItem('accessToken')),
    });
  }
}
