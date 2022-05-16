import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-appointment-card',
  templateUrl: './appointment-card.component.html',
  styleUrls: ['./appointment-card.component.css']
})
export class AppointmentCardComponent {

  @Input('medicalServiceName') medicalServiceName: string;

  constructor() { }
}
