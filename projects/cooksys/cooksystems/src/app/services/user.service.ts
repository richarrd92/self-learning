import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { ProfileDto, UserStateDto, UserRequestDto, UserCredentialsDto } from './announcement.service';

export interface UserResponseDto {
  id: number;
  credentials: UserCredentialsDto;
  profile: ProfileDto;
  userState: UserStateDto;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private apiService: ApiService) {}

  getAllUsers(): Observable<UserResponseDto[]> {
    return this.apiService.get<UserResponseDto[]>('/users');
  }

  getUser(id: number): Observable<UserResponseDto> {
    return this.apiService.get<UserResponseDto>(`/users/${id}`);
  }

  getUsersByCompany(companyId: number): Observable<UserResponseDto[]> {
    return this.apiService.get<UserResponseDto[]>(`/users/company/${companyId}`);
  }

  createUser(user: UserRequestDto, companyId: number): Observable<UserResponseDto> {
    return this.apiService.post<UserResponseDto>(`/users?companyId=${companyId}`, user);
  }
}
