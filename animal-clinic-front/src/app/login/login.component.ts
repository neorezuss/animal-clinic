import {Component} from '@angular/core';
import {NgForm} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {catchError, throwError} from "rxjs";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  emailAndPasswordAreValid: boolean = true
  emailExists: boolean = true;

  constructor(private authService: AuthService, private router: Router, private modalService: NgbModal) {
  }

  onSubmit(form: NgForm) {
    for (let key in form.form.controls) {
      form.form.controls[key].markAsTouched()
    }

    if (!form.valid)
      return;

    this.authService.logIn({email: form.form.value.email, password: form.form.value.password})
      .pipe(
        catchError(err => {
          this.emailAndPasswordAreValid = false
          return throwError(err)
        }))
      .subscribe(
        data => {
          localStorage.setItem('accessToken', data.accessToken)
          localStorage.setItem('refreshToken', data.refreshToken)
          this.authService.refreshIsLoggedIn()
          this.router.navigate(['/home']);
        });
  }

  openResetPasswordModal(resetPasswordModal: any) {
    this.modalService.open(resetPasswordModal);
  }

  onPasswordResetSubmit(form: NgForm) {
    for (let key in form.form.controls) {
      form.form.controls[key].markAsTouched()
    }

    if (!form.valid)
      return;

    this.authService.resetPassword({email: form.form.value.email})
      .pipe(
        catchError(err => {
          this.emailExists = false
          return throwError(err)
        }))
      .subscribe(
        data => {
          this.modalService.dismissAll()
        });
  }
}
