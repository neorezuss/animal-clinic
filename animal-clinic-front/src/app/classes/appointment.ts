import {Specialist} from "./specialist";

export class Appointment {
  id: number;
  name: string;
  specialist: Specialist | null;
  date: string;
  time: string;
  officeNumber: number;
  result: string | null;
}
