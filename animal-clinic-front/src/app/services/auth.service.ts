import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { AuthResponse } from "../classes/auth-response";
import { Registration } from "../classes/registration";
import { Login } from "../classes/login";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authControllerUrl: string = 'http://localhost:8080/api/v1/auth';

  isLoggedIn: boolean = false;

  constructor(private http: HttpClient) {
    this.isLoggedIn = localStorage.getItem('accessToken') !== null
      && localStorage.getItem('refreshToken') !== null
  }

  refreshIsLoggedIn(): void {
    this.isLoggedIn = localStorage.getItem('accessToken') !== null
      && localStorage.getItem('refreshToken') !== null
  }

  loginIn(login: Login) {
    return this.http.post<AuthResponse>(this.authControllerUrl + '/login', login)
  }

  loginOut(): void {
    localStorage.removeItem('accessToken')
    localStorage.removeItem('refreshToken')
    this.refreshIsLoggedIn()
  }

  register(registration: Registration) {
    return this.http.post<Registration>(this.authControllerUrl + '/register', registration)
  }

  refreshTokens() {
    return this.http.post<AuthResponse>(this.authControllerUrl + '/refresh-token',
      localStorage.getItem('refreshToken'))
  }
}
