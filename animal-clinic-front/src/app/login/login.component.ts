import { Component } from '@angular/core';
import { NgForm } from "@angular/forms";
import { AuthService } from "../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private authService: AuthService) {
  }

  onSubmit(form: NgForm) {
    this.authService.login({email: form.form.value.email, password: form.form.value.password})
      .subscribe(data => {
        localStorage.setItem('accessToken', data.accessToken)
        localStorage.setItem('refreshToken', data.refreshToken)
        console.log(data)
      });
  }
}
