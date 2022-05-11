import {Component, ViewChild} from '@angular/core';
import { NgForm } from "@angular/forms";
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
    this.authService.resetPassword({email: form.form.value.email})
      .subscribe(
        data => {
          this.modalService.dismissAll()
        },
        error => {
          this.emailExists = false
        });
  }
}
