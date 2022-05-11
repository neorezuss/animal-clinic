import {Component} from '@angular/core';
import {NgForm} from "@angular/forms";
import {UserProfile} from "../../classes/user-profile";
import {ProfileService} from "../../services/profile.service";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent {

  editMode: boolean = false
  emailAndPasswordAreValid: boolean = true
  userProfile: UserProfile

  constructor(private profileService: ProfileService,
              private authService: AuthService,
              private router: Router,
              private modalService: NgbModal) {
    this.profileService.getUserProfile().subscribe(data => {
      this.userProfile = data;
    });
  }

  onSubmit(form: NgForm) {
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

  changePassword(changePasswordModal: any) {
    this.modalService.open(changePasswordModal);
  }

  onChangePasswordSubmit(form: NgForm) {

  }

  edit() {
    this.editMode = true
  }

  cancel() {
    this.editMode = false
  }
}
