import { Pet } from "./pet";

export class UserProfile {
  firstName: string;
  lastName: string;
  patronymic: string;
  gender: string;
  birthDate: string;
  phoneNumber: string;
  email: string;
  pets: Pet[] | null;
}
