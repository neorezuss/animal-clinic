import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MedicalServiceService } from "../services/medical-service.service";
import { MedicalService } from "../classes/medical-service";
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PetType } from "../classes/pet-type-enum";
import { AuthService } from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-medical-services',
  templateUrl: './medical-services.component.html',
  styleUrls: ['./medical-services.component.css']
})
export class MedicalServicesComponent implements OnInit {

  medicalServices: MedicalService[];
  filteredMedicalServices: MedicalService[];
  isFetched: boolean = false;
  petType: any = PetType;
  @ViewChild('searchBar', {static: true}) search: ElementRef;
  @ViewChild('modal', {static: true}) modal: ElementRef;

  constructor(private medicalServiceService: MedicalServiceService, private router: Router,
              private modalService: NgbModal, public authService: AuthService) { }

  ngOnInit(): void {
    this.medicalServiceService.getMedicalServiceTypes().subscribe(data => {
      this.medicalServices = data;
      this.filteredMedicalServices = this.medicalServices
      this.isFetched = true
      //setTimeout(()=> this.isFetched = true, 1000)
    });
  }

  onInput(): void {
    this.filteredMedicalServices = this.medicalServices.filter(
      service => service.name.toLowerCase()
        .includes(
          this.search.nativeElement.value.toLocaleLowerCase()))
  }

  onCardClick(content: any) {
    this.modalService.open(content, { size: 'lg' , scrollable: true });
  }

  makeAppointment(name: string) {
    this.modalService.dismissAll()
    this.router.navigate(['/profile/appointments/make-appointment/' + name]);
  }
}
