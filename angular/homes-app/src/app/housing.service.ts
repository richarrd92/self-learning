import { Injectable } from "@angular/core";
import { HousingLocation } from "./housing-location";

@Injectable({
  providedIn: "root",
})
export class HousingService {
  url = "http://localhost:3000/locations";

  constructor() {}

  async getAllHousingLocations(): Promise<HousingLocation[]> {
    const data = await fetch(this.url);
    return await data.json() as HousingLocation[] ?? [];
  }

  async getHousingLocationById(id: number): Promise<HousingLocation | undefined>{
    const data = await fetch(`${this.url}/${id}`);
    return await data.json() as HousingLocation ?? {};
  }

  submitApplication(firstname: string, lastname: string, email: string) {
    console.log(
      `Homes application received: firstname: ${firstname}, lastname: ${lastname}, email: ${email}.`
    );
  }
}
