import {Component, Input, OnInit} from '@angular/core';
import {Pet} from "../../../classes/pet";
import {PetType} from "../../../classes/pet-type-enum";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PetService} from "../../../services/pet.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-history-pet-card',
  templateUrl: './history-pet-card.component.html',
  styleUrls: ['./history-pet-card.component.css']
})
export class HistoryPetCardComponent {

  @Input('pet') pet: Pet;
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
}
