import {Component} from '@angular/core';
import {MedicalServiceService} from "../../services/medical-service.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PetService} from "../../services/pet.service";
import {PetAppointments} from "../../classes/pet-appointments";

@Component({
  selector: 'app-appointments-history',
  templateUrl: './appointments-history.component.html',
  styleUrls: ['./appointments-history.component.css']
})
export class AppointmentsHistoryComponent {

  petAppointments: PetAppointments[];

  constructor(private medicalServiceService: MedicalServiceService,
              private modalService: NgbModal, private petService: PetService) {
    this.petService.getPetAppointments()
      .subscribe(data => {
        this.petAppointments = data;
      });
  }

  openModal(modal: any) {
    this.modalService.open(modal);
  }
}
