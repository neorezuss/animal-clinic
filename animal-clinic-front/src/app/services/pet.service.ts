import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Pet} from "../classes/pet";
import {PetAppointments} from "../classes/pet-appointments";

@Injectable({
  providedIn: 'root'
})
export class PetService {
  private petControllerUrl: string = 'http://localhost:30081/api/v1/pets';

  constructor(private http: HttpClient) {
  }

  public getPets(): Observable<Pet[]> {
    return this.http.get<Pet[]>(this.petControllerUrl);
  }

  public addPet(pet: Pet): Observable<Pet> {
    return this.http.post<Pet>(this.petControllerUrl, pet);
  }

  public updatePet(pet: Pet): Observable<Pet> {
    return this.http.put<Pet>(this.petControllerUrl, pet);
  }

  public deletePet(petId: number): Observable<{ petId: number }> {
    return this.http.delete<{ petId: number }>(this.petControllerUrl + `/${petId}`);
  }

  public getPetAppointments(): Observable<PetAppointments[]> {
    return this.http.get<PetAppointments[]>(this.petControllerUrl + '/appointments');
  }
}
