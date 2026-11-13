import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { UserRequestDto } from './announcement.service';

export interface TeamDto {
  id: number;
  name: string;
  description: string;
  users: UserRequestDto[];
}

export interface CreateTeamDto {
  authorId: number;
  name: string;
  description: string;
  userIds: number[];
}

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  constructor(private apiService: ApiService) {}

  getAllTeams(companyId: number): Observable<TeamDto[]> {
    return this.apiService.get<TeamDto[]>(`/teams/${companyId}`);
  }

  createTeam(companyId: number, team: CreateTeamDto): Observable<TeamDto> {
    return this.apiService.post<TeamDto>(`/teams/${companyId}`, team);
  }
}
