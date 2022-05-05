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

  constructor(private http: HttpClient) { }

  isLoggedIn(): boolean {
    return localStorage.getItem('accessToken') !== null
      && localStorage.getItem('refreshToken') !== null
  }

  login(login: Login) {
    return this.http.post<AuthResponse>(this.authControllerUrl + '/login', login);
  }

  register(registration: Registration) {
    return this.http.post<Registration>(this.authControllerUrl + '/register', registration);
  }
}
