export class MedicalService {
  name: string;
  image: string;
  shortDescription: string;
  longDescription: string;
  medicalServicePrices: { petType: string, price: number }[];
}
