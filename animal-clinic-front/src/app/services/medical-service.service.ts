import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MedicalService} from "../classes/medical-service";
import {MedicalServiceSchedule} from "../classes/medical-service-schedule";

@Injectable({
  providedIn: 'root'
})
export class MedicalServiceService {

  private medicalServicesUrl: string = 'http://localhost:8080/api/v1/medical-services';

  constructor(private http: HttpClient) { }

  public getMedicalServiceTypes(): Observable<MedicalService[]> {
    return this.http.get<MedicalService[]>(this.medicalServicesUrl + '/types');
  }

  public getMedicalServiceTypeNames(): Observable<string[]> {
    return this.http.get<string[]>(this.medicalServicesUrl + '/type-names');
  }

  public getMedicalServiceSchedules(name: string): Observable<MedicalServiceSchedule[]> {
    return this.http.get<MedicalServiceSchedule[]>(this.medicalServicesUrl + '/schedules/' + name);
  }

  public makeAppointment(ids: { medicalServiceId: number, petId: number }): Observable<{ medicalServiceId: number, petId: number }> {
    return this.http.post<{ medicalServiceId: number, petId: number }>(this.medicalServicesUrl + '/make-appointment', ids);
  }

  public cancelAppointment(ids: { medicalServiceId: number, petId: number }): Observable<{ medicalServiceId: number, petId: number }> {
    return this.http.post<{ medicalServiceId: number, petId: number }>(this.medicalServicesUrl + '/cancel-appointment', ids);
  }
}
