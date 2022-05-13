import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { AuthResponse } from "../classes/auth-response";
import { Registration } from "../classes/registration";
import { Login } from "../classes/login";
import {catchError, Observable, throwError} from "rxjs";
import {Router} from "@angular/router";
import {ChangePassword} from "../classes/change-password";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authControllerUrl: string = 'http://localhost:8080/api/v1/auth';

  isLoggedIn: boolean = false;

  constructor(private http: HttpClient, private router: Router) {
    this.isLoggedIn = localStorage.getItem('accessToken') !== null
      && localStorage.getItem('refreshToken') !== null
  }

  refreshIsLoggedIn(): void {
    this.isLoggedIn = localStorage.getItem('accessToken') !== null
      && localStorage.getItem('refreshToken') !== null
  }

  logIn(login: Login): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.authControllerUrl + '/login', login)
  }

  logOut(): void {
    localStorage.removeItem('accessToken')
    localStorage.removeItem('refreshToken')
    this.router.navigate(['/home'])
    this.refreshIsLoggedIn()
  }

  register(registration: Registration): Observable<Registration> {
    return this.http.post<Registration>(this.authControllerUrl + '/register', registration)
  }

  refreshTokens(): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.authControllerUrl + '/refresh-token',
      { refreshToken:   localStorage.getItem('refreshToken') })
      .pipe(
        catchError(err => {
          this.logOut()
          this.router.navigate(['/login'])
          return throwError(err)
        })
      )
  }

  resetPassword(email: { email: string }): Observable<boolean> {
    return this.http.post<boolean>(this.authControllerUrl + '/reset-password', email)
  }

  changePassword(changePassword: ChangePassword): Observable<boolean> {
    return this.http.post<boolean>(this.authControllerUrl + '/change-password', changePassword)
  }
}
