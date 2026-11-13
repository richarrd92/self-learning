import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

export interface TeamDto {
  id: number;
  name: string;
  description: string;
}

export interface UserRequestDto {
  id: number;
  username: string;
  email: string;
  admin: boolean;
}

export interface CompanyDto {
  id: number;
  name: string;
  description: string;
  teams: TeamDto[];
  users: UserRequestDto[];
}

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  constructor(private apiService: ApiService) {}

  getAllCompanies(): Observable<CompanyDto[]> {
    return this.apiService.get<CompanyDto[]>('/companies');
  }
}
