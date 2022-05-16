import {Component} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MedicalServiceSchedule} from "../../../classes/medical-service-schedule";
import {MedicalServiceService} from "../../../services/medical-service.service";
import {WeekDay} from "../../../classes/week-day-enum";
import {Specialist} from "../../../classes/specialist";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PetService} from "../../../services/pet.service";
import {NgForm} from "@angular/forms";
import {Pet} from "../../../classes/pet";
import {PetTypeName} from "../../../classes/pet-type-name-enum";

@Component({
  selector: 'app-make-appointment',
  templateUrl: './make-appointment.component.html',
  styleUrls: ['./make-appointment.component.css']
})
export class MakeAppointmentComponent {

  serviceName: string;
  medicalServiceSchedules: MedicalServiceSchedule[];
  medicalServiceScheduleDates: string[];
  userPets: Pet[];
  userValidPets: Pet[];
  weekDayEnum: any = WeekDay;
  petTypeNameEnum: any = PetTypeName;
  userDoesntHavePetsForThisService: boolean;

  constructor(private route: ActivatedRoute, private medicalServiceService: MedicalServiceService,
              private modalService: NgbModal, private petService: PetService) {

    this.serviceName = route.snapshot.params['name']

    this.medicalServiceService.getMedicalServiceSchedules(this.serviceName)
      .subscribe(data => {
        this.medicalServiceSchedules = data;
        this.medicalServiceScheduleDates = [...new Set(this.medicalServiceSchedules.map(item => item.date))]
      });

    this.petService.getPets()
      .subscribe(data => {
        this.userPets = data;
      });
  }

  filterMedicalServiceSchedulesByDate(date: string) {
    return this.medicalServiceSchedules.filter(item => item.date == date)
  }

  parseTime(time: string) {
    return time.substring(0, 5)
  }

  getWeekDay(stringDate: string) {
    const date: Date = new Date(stringDate)

    return this.weekDayEnum['d' + date.getDay()]
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

  openModal(modal: any, schedule: MedicalServiceSchedule) {
    this.userValidPets = this.userPets.filter(pet => schedule.availablePetTypes.includes(pet.petType))
    this.userDoesntHavePetsForThisService = this.userValidPets.length == 0
    this.modalService.open(modal);
  }

  onAcceptModalSubmit(form: NgForm, schedule: MedicalServiceSchedule) {
    for (let key in form.form.controls) {
      form.form.controls[key].markAsTouched()
    }

    if (!form.valid)
      return;

    this.medicalServiceService.makeAppointment({
      medicalServiceId: form.form.value.medicalServiceId,
      petId: form.form.value.petId,
    }).subscribe(
      data => {
        this.modalService.dismissAll()
        this.medicalServiceSchedules.splice(this.medicalServiceSchedules
          .findIndex(schedule => schedule.id == form.form.value.medicalServiceId), 1)
      });
  }
}
