import {Component, Input,} from '@angular/core';
import {Appointment} from "../../../classes/appointment";
import {Specialist} from "../../../classes/specialist";
import {MedicalServiceService} from "../../../services/medical-service.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-appointment-history-card',
  templateUrl: './appointment-history-card.component.html',
  styleUrls: ['./appointment-history-card.component.css']
})
export class AppointmentHistoryCardComponent {


  @Input('appointment') appointment: Appointment;
  @Input('appointments') appointments: Appointment[];
  @Input('petId') petId: number;

  constructor(private medicalServiceService: MedicalServiceService, private modalService: NgbModal) {
  }

  openModal(modal: any) {
    this.modalService.open(modal);
  }

  parseTime(time: string) {
    return time.substring(0, 5)
  }

  formatDate(stringDate: string) {
    const date: Date = new Date(stringDate)

    let dd: any = date.getDate();
    if (dd < 10) dd = '0' + dd;

    let mm: any = date.getMonth() + 1;
    if (mm < 10) mm = '0' + mm;

    let yyyy: number = date.getFullYear();

    return dd + '.' + mm + '.' + yyyy;
  }

  getSpecialistSurnameAndInitials(specialist: Specialist | null) {
    if (specialist == null) {
      return '';
    }

    return specialist.lastName + ' ' + specialist.firstName.charAt(0) + '.' + specialist.patronymic.charAt(0) + '.'
  }

  hasResult(appointment: Appointment) {
    return appointment.result != null
  }

  canBeCancelled(stringDate: string) {
    const date: Date = new Date(stringDate)
    const today: Date = new Date()
    return date > today
  }

  onCancelAppointment() {
    this.medicalServiceService.cancelAppointment({
      medicalServiceId: this.appointment.id,
      petId: this.petId
    })
      .subscribe(
        data => {
          this.modalService.dismissAll()
          this.appointments.splice(this.appointments.findIndex(appointment => appointment.id == this.appointment.id), 1)
        });
  }
}
