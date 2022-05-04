import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {MedicalService} from "../../classes/medical-service";
declare var $: any;

@Component({
  selector: 'app-medical-service-card',
  templateUrl: './medical-service-card.component.html',
  styleUrls: ['./medical-service-card.component.css']
})
export class MedicalServiceCardComponent implements OnInit {

  @Input('medicalService') medicalService: MedicalService;
  @ViewChild('image', {static: true}) image: ElementRef;
  @ViewChild('modal', {static: true}) modal: ElementRef;


  constructor() {}

  ngOnInit(): void {
    this.image.nativeElement.src = 'data:image/jpeg;base64, ' + this.medicalService.image
  }
}
