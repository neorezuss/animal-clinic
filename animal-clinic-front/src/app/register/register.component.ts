import {Component, OnInit, ViewChild} from '@angular/core';
import {NgForm, NgModel} from "@angular/forms";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  genderNotSelected: boolean = false
  passwordsAreDifferent: boolean = false
  @ViewChild('password', {static: true}) password: NgModel;
  @ViewChild('repeatPassword', {static: true}) repeatPassword: NgModel;
  @ViewChild('gender', {static: true}) gender: NgModel;

  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    if (this.gender.value === '') {
      this.genderNotSelected = true
    }

    if (this.genderNotSelected || this.passwordsAreDifferent || !form.valid)
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
    }).subscribe(data => {
      console.log(data)
    });
  }

  onRadioChange() {
    this.genderNotSelected = false
  }

  onInput() {
    this.passwordsAreDifferent = this.password.value != this.repeatPassword.value
  }
}
