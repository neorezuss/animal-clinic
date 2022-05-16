import {Component, Input} from '@angular/core';
import {PetType} from "../../../../classes/pet-type-enum";
import {Pet} from "../../../../classes/pet";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PetService} from "../../../../services/pet.service";
import {NgForm} from "@angular/forms";

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

  onDeletePetModalSubmit() {
    this.petService.deletePet(this.pet.id)
      .subscribe(
        data => {
          this.pets?.splice(this.pets?.findIndex(pet => pet === this.pet), 1)
          this.modalService.dismissAll()
        });
  }

  onUpdatePetModalSubmit(form: NgForm) {
    for (let key in form.form.controls) {
      form.form.controls[key].markAsTouched()
    }

    if (!form.valid)
      return;

    this.petService.updatePet(form.form.value)
      .subscribe(
        data => {
          this.modalService.dismissAll()
          const index: any = this.pets?.findIndex(pet => pet.id == form.form.value.id)
          this.pets?.splice(index, 1, form.form.value)
          this.pets?.sort((p1, p2) => p1.name > p2.name ? 1 : -1)
        });
  }
}
