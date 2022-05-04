import { Injectable } from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {AuthResponse} from "../classes/auth-response";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authControllerUrl: string = 'http://localhost:8080/api/v1/auth';

  constructor(private http: HttpClient) { }

  login(email: string, password: string) {
    return this.http.post<AuthResponse>(this.authControllerUrl + '/login', { email, password });
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('accessToken') !== null
      && localStorage.getItem('refreshToken') !== null
  }


}
