import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {Pet} from "../../../classes/pet";
import {FormControl, NgForm, NgModel} from "@angular/forms";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PetService} from "../../../services/pet.service";
import {catchError, throwError} from "rxjs";

@Component({
  selector: 'app-pets-info',
  templateUrl: './pets-info.component.html',
  styleUrls: ['./pets-info.component.css']
})
export class PetsInfoComponent {

  @Input() pets: Pet[] | null;
  @ViewChild('petType', {static: true}) petType: NgModel;

  constructor(private modalService: NgbModal, private petService: PetService) { }

  openModal(modal: any) {
    this.modalService.open(modal);
  }

  onAddPetModalSubmit(form: NgForm) {
    for (let key in form.form.controls) {
      form.form.controls[key].markAsTouched()
    }

    if (!form.valid)
      return;

    this.petService.addPet({
      id: 0,
      name: form.form.value.name,
      petType: form.form.value.petType,
      breed: form.form.value.breed,
      birthDate: form.form.value.birthDate,
    })
      .subscribe(
        data => {
          this.modalService.dismissAll()
          const insertIndex: any = this.pets?.findIndex(pet => pet.name > form.form.value.name)
          this.pets?.splice(insertIndex < 0 ? this.pets?.length : insertIndex, 0, data)
        });
  }
}
