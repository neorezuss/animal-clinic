import {Component} from '@angular/core';
import {UserProfile} from "../../classes/user-profile";
import {ProfileService} from "../../services/profile.service";


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent {

  userProfile: UserProfile;

  constructor(private profileService: ProfileService) {
    this.profileService.getUserProfile().subscribe(data => {
      this.userProfile = data;
      this.userProfile.pets?.sort((p1, p2) => p1.name > p2.name ? 1 : -1)
    });
  }
}
