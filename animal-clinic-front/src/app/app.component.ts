import {Component, OnChanges, SimpleChanges} from '@angular/core';
import {AuthService} from "./services/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnChanges {
  title = 'animal-clinic-front';
  isLoggedIn: boolean;

  constructor(public authService: AuthService) {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.isLoggedIn = this.authService.isLoggedIn()
  }
}
