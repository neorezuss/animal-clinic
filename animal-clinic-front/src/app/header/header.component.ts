import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  title = 'animal-clinic-front';

  constructor(public authService: AuthService) {
  }

  onLogOut() {
    this.authService.logOut()
  }
}
