import {Appointment} from "./appointment";

export class PetAppointments {
  id: number;
  name: string;
  petType: string;
  breed: string;
  birthDate: string;
  appointments: Appointment[];
}
