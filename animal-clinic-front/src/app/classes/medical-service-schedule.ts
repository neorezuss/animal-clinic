import {Specialist} from "./specialist";

export class MedicalServiceSchedule {
  id: number;
  specialist: Specialist | null;
  availablePetTypes: string[];
  date: string;
  time: string;
  officeNumber: number;
}
