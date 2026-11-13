import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

export interface ProjectDto {
  id: number;
  name: string;
  description: string;
  active: boolean;
  team: {
    id: number;
    name: string;
    description: string;
  };
}

export interface CreateProjectDto {
  name: string;
  description: string;
  active: boolean;
}

export interface UpdateProjectDto {
  name: string;
  description: string;
  active: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  constructor(private apiService: ApiService) {}

  // Get all projects for a team
  getAllProjects(companyId: number, teamId: number): Observable<ProjectDto[]> {
    return this.apiService.get<ProjectDto[]>(`/projects/${companyId}/${teamId}`);
  }

  // Create a new project
  createProject(companyId: number, teamId: number, project: CreateProjectDto): Observable<ProjectDto> {
    return this.apiService.post<ProjectDto>(`/projects/${companyId}/${teamId}`, project);
  }

  // Update an existing project
  updateProject(companyId: number, teamId: number, projectId: number, project: UpdateProjectDto): Observable<ProjectDto> {
    return this.apiService.patch<ProjectDto>(`/projects/${companyId}/${teamId}/${projectId}`, project);
  }
}
