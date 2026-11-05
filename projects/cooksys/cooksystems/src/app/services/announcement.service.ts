import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

export interface ProfileDto {
  first: string;
  last: string;
  phone: string;
}

export interface UserStateDto {
  active: boolean;
  admin: boolean;
  status: string;
}

export interface CredentialsRequestDto {
  username: string;
  password: string;
  email: string;
}

export interface UserCredentialsDto {
  username: string;
  email: string;
}

export interface UserRequestDto {
  credentials: CredentialsRequestDto;
  profile: ProfileDto;
  userState: UserStateDto;
}

export interface CompanyDto {
  id: number;
  name: string;
  description: string;
}

export interface AnnouncementDto {
  id: number;
  title: string;
  message: string;
  author: UserRequestDto;
  date: string;
  company: CompanyDto;
}

export interface CreateAnnouncementDto {
  authorId: number;
  title: string;
  message: string;
}

@Injectable({
  providedIn: 'root'
})
export class AnnouncementService {
  constructor(private apiService: ApiService) {}

  getAllAnnouncements(companyId: number): Observable<AnnouncementDto[]> {
    return this.apiService.get<AnnouncementDto[]>(`/announcements/${companyId}`);
  }

  createAnnouncement(companyId: number, announcement: CreateAnnouncementDto): Observable<AnnouncementDto> {
    return this.apiService.post<AnnouncementDto>(`/announcements/${companyId}`, announcement);
  }
}
