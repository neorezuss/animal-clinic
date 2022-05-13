import {Component, Input} from '@angular/core';
import {PetType} from "../../../../classes/pet-type-enum";
import {Pet} from "../../../../classes/pet";
import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PetService} from "../../../../services/pet.service";

@Component({
  selector: 'app-pet-card',
  templateUrl: './pet-card.component.html',
  styleUrls: ['./pet-card.component.css']
})
export class PetCardComponent {

  @Input('pet') pet: Pet;
  @Input('pets') pets: Pet[] | null;
  petType: any = PetType;

  constructor(private modalService: NgbModal, private petService: PetService) {
  }

  formatDate(stringDate: string) {
    let date: Date = new Date(stringDate)

    let dd: any = date.getDate();
    if (dd < 10) dd = '0' + dd;

    let mm: any = date.getMonth() + 1;
    if (mm < 10) mm = '0' + mm;

    let yyyy: number = date.getFullYear();

    return dd + '.' + mm + '.' + yyyy;
  }

  openModal(modal: any) {
    this.modalService.open(modal);
  }

  deletePet() {
    this.petService.deletePet(this.pet.id)
      .subscribe(
        data => {
          this.pets?.splice(this.pets?.findIndex(pet => pet === this.pet), 1)
          this.modalService.dismissAll()
        });
  }

  updatePet() {

  }
}
