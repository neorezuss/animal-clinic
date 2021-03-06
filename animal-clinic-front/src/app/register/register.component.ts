import {Component, ViewChild} from '@angular/core';
import {NgForm, NgModel} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {catchError, throwError} from "rxjs";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  genderNotSelected: boolean = false
  passwordsAreDifferent: boolean = false
  emailIsAvailable: boolean = true
  @ViewChild('password', {static: true}) password: NgModel;
  @ViewChild('repeatPassword', {static: true}) repeatPassword: NgModel;
  @ViewChild('gender', {static: true}) gender: NgModel;

  constructor(private authService: AuthService, private modalService: NgbModal) {
  }

  onSubmit(form: NgForm, modal: any) {
    for (let key in form.form.controls) {
      form.form.controls[key].markAsTouched()
    }

    if (this.gender.value === '') {
      this.genderNotSelected = true
    }

    if (this.genderNotSelected || this.passwordsAreDifferent || !form.valid || !this.emailIsAvailable)
      return;

    this.authService.register({
      firstName: form.form.value.firstName,
      lastName: form.form.value.lastName,
      patronymic: form.form.value.patronymic,
      gender: form.form.value.gender,
      birthDate: form.form.value.birthDate,
      phoneNumber: form.form.value.phoneNumber,
      email: form.form.value.email,
      password: form.form.value.password,
    })
      .pipe(
        catchError(err => {
          this.emailIsAvailable = false
          return throwError(err)
        }))
      .subscribe(
        data => {
          this.modalService.open(modal);
        });
  }

  onRadioChange() {
    this.genderNotSelected = false
  }

  onInput() {
    this.passwordsAreDifferent = this.password.value != this.repeatPassword.value
  }
}
