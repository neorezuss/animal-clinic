import {Component, Input, ViewChild} from '@angular/core';
import {UserProfile} from "../../../classes/user-profile";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {ProfileService} from "../../../services/profile.service";
import {AuthService} from "../../../services/auth.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {catchError, throwError} from "rxjs";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent {
  editMode: boolean = false;
  emailAndPasswordAreValid: boolean = true;
  @Input('userProfile') userProfile: UserProfile;
  passwordsAreDifferent: boolean = false
  @ViewChild('acceptModal', {static: true}) acceptModal: NgbModalRef;

  constructor(private profileService: ProfileService,
              private authService: AuthService,
              private router: Router,
              private modalService: NgbModal) {
/*    this.profileService.getUserProfile().subscribe(data => {
      this.userProfile = data;
    });*/
  }

  onProfileUpdateSubmit(form: NgForm) {
    for (let key in form.form.controls) {
      form.form.controls[key].markAsTouched()
    }

    if (!form.valid) {
      return;
    }

    this.profileService.updateUserProfile({
      firstName: form.form.value.firstName,
      lastName: form.form.value.lastName,
      patronymic: form.form.value.patronymic,
      gender: form.form.value.gender,
      birthDate: form.form.value.birthDate,
      phoneNumber: form.form.value.phoneNumber,
      email: form.form.value.email,
      pets: null
    }).subscribe(
      data => {
        this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
          this.router.navigate(['/profile/user-profile']);
        });
      });
  }

  onChangePasswordSubmit(form: NgForm) {
    for (let key in form.form.controls) {
      form.form.controls[key].markAsTouched()
    }

    if (!form.valid || this.passwordsAreDifferent) {
      return;
    }

    this.authService.changePassword({
      email: form.form.value.email,
      oldPassword: form.form.value.oldPassword,
      newPassword: form.form.value.newPassword
    })
      .pipe(
        catchError(err => {
          this.emailAndPasswordAreValid = false
          return throwError(err)
        }))
      .subscribe(
        data => {
          this.modalService.dismissAll()
          this.openChangePasswordAcceptModal(this.acceptModal)
        });
  }

  updatePasswordsFlag(form: NgForm) {
    this.passwordsAreDifferent = form.form.value.newPassword != form.form.value.repeatNewPassword
  }

  openChangePasswordModal(changePasswordModal: any) {
    this.modalService.open(changePasswordModal);
  }

  openChangePasswordAcceptModal(acceptModal: any) {

    this.modalService.open(acceptModal);
  }

  edit() {
    this.editMode = true
  }

  cancel() {
    this.editMode = false
  }

  formatDate(stringDate: string) {
    let date: Date = new Date(stringDate)

    let dd: any = date.getDate();
    if (dd < 10) dd = '0' + dd;

    let mm: any = date.getMonth() + 1;
    if (mm < 10) mm = '0' + mm;

    let yyyy: number = date.getFullYear();

    return dd + '.' + mm + '.' + yyyy;
  }
}
