import {Component, ElementRef, ViewChild} from '@angular/core';
import {NgForm, NgModel} from "@angular/forms";
import {UserProfile} from "../../classes/user-profile";
import {ProfileService} from "../../services/profile.service";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {catchError, throwError} from "rxjs";
import {Pet} from "../../classes/pet";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent {

  userProfile: UserProfile;
  pets: Pet[];

  constructor(private profileService: ProfileService) {
    this.profileService.getUserProfile().subscribe(data => {
      this.userProfile = data;
      // @ts-ignore
      this.pets = this.userProfile.pets?.sort((p1, p2) => p1.name > p2.name ? 1 : -1)
    });
  }
}
