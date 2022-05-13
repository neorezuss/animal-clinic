import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Pet} from "../classes/pet";

@Injectable({
  providedIn: 'root'
})
export class PetService {
  private petControllerUrl: string = 'http://localhost:8080/api/v1/pets';

  constructor(private http: HttpClient) {
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
}
