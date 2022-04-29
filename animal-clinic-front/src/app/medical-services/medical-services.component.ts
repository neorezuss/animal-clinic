import { Component, OnInit } from '@angular/core';
import { MedicalServiceService } from "../services/medical-service.service";
import { MedicalService } from "../classes/medical-service";

@Component({
  selector: 'app-medical-services',
  templateUrl: './medical-services.component.html',
  styleUrls: ['./medical-services.component.css']
})
export class MedicalServicesComponent implements OnInit {

  medicalServices: MedicalService[];
  isFetched: boolean = false;

  constructor(private medicalServiceService: MedicalServiceService) { }

  ngOnInit(): void {
    this.medicalServiceService.findAll().subscribe(data => {
      this.medicalServices = data;
      console.log(this.medicalServices)
      this.isFetched = true
    });
  }
}
