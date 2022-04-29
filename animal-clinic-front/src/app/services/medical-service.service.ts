import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MedicalService} from "../classes/medical-service";

@Injectable({
  providedIn: 'root'
})
export class MedicalServiceService {

  private medicalServicesUrl: string = 'http://localhost:8080/api/v1/medical-services';

  constructor(private http: HttpClient) { }

  public findAll(): Observable<MedicalService[]> {
    return this.http.get<MedicalService[]>(this.medicalServicesUrl);
  }
}
