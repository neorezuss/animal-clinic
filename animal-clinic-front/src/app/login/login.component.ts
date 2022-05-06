import {Component} from '@angular/core';
import {NgForm} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  emailAndPasswordAreValid: boolean = true

  constructor(private authService: AuthService, private router: Router) {
  }

  onSubmit(form: NgForm) {
    this.authService.loginIn({email: form.form.value.email, password: form.form.value.password})
      .subscribe(
        data => {
          localStorage.setItem('accessToken', data.accessToken)
          localStorage.setItem('refreshToken', data.refreshToken)
          this.authService.refreshIsLoggedIn()
          this.router.navigate(['/home']);
        },
        error => {
          this.emailAndPasswordAreValid = false
        });
  }
}
