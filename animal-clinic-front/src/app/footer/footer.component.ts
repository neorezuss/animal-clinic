import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnChanges {

  isLoggedIn: boolean;

  constructor(private authService: AuthService) { }

  ngOnChanges(changes: SimpleChanges): void {
    this.isLoggedIn = this.authService.isLoggedIn()
  }

}
