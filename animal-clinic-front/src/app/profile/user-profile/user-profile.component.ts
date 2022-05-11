import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {UserProfile} from "../../classes/user-profile";
import {ProfileService} from "../../services/profile.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  editMode: boolean = false
  userProfile: UserProfile

  constructor(private profileService: ProfileService, private authService: AuthService) {
    this.profileService.getUserProfile().subscribe(data => {
      this.userProfile = data;
      console.log(data)
    });
  }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {

  }
}
