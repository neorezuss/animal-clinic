import {Component, ElementRef, ViewChild} from '@angular/core';
import {MedicalServiceService} from "../../../services/medical-service.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Router} from "@angular/router";

@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.css']
})
export class AppointmentListComponent {

  medicalServiceNames: string[];
  filteredMedicalServiceNames: string[];
  @ViewChild('searchBar', {static: true}) search: ElementRef;

  constructor(private medicalServiceService: MedicalServiceService, private router: Router) {
    this.medicalServiceService.getMedicalServiceTypeNames().subscribe(data => {
      this.medicalServiceNames = data;
      this.filteredMedicalServiceNames = data;
    });
  }

  onInput(): void {
    this.filteredMedicalServiceNames = this.medicalServiceNames.filter(
      service => service.toLowerCase()
        .includes(
          this.search.nativeElement.value.toLocaleLowerCase()))
  }

  onCardClick(name: string) {
    this.router.navigate(['/profile/appointments/make-appointment/' + name]);
  }
}
