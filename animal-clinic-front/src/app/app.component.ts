import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {AuthService} from "./services/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'animal-clinic-front';

  constructor(public authService: AuthService) {
  }

  onLogOut() {
    this.authService.logOut()
  }
}
