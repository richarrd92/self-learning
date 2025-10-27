import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { ProjectService, ProjectDto } from '../services/project.service';
import { CreateProjectModalComponent } from './create-project-modal/create-project-modal';
import { EditProjectModalComponent } from './edit-project-modal/edit-project-modal';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { NavbarComponent } from '../shared/navbar/navbar.component';

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatDialogModule, NavbarComponent],
  templateUrl: './projects.html',
  styleUrl: './projects.css',
})
export class Projects implements OnInit {
  projects: ProjectDto[] = [];
  isLoading: boolean = true;
  errorMessage: string = '';
  isAdmin: boolean = false;
  username: string = '';
  companyId: number = 1; // Will be set from query params
  teamId: number = 1; // Will be set from query params
  teamName: string = 'Team 1'; // Will be set from query params
  userId: number = 1; // Will be set from auth service

  constructor(
    private projectService: ProjectService,
    private dialog: MatDialog,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    // Get user data from auth service
    const userData = this.authService.getUserData();
    if (userData) {
      this.username = userData.username;
      this.isAdmin = userData.admin;
      this.userId = userData.id;
    }

    // Get companyId, teamId, and teamName from localStorage
    const storedCompanyId = localStorage.getItem('selectedCompanyId');
    if (storedCompanyId) {
      this.companyId = +storedCompanyId;
    }

    const storedTeamId = localStorage.getItem('selectedTeamId');
    if (storedTeamId) {
      this.teamId = +storedTeamId;
    }

    const storedTeamName = localStorage.getItem('selectedTeamName');
    if (storedTeamName) {
      this.teamName = storedTeamName;
    }

    this.loadProjects();
  }

  loadProjects(): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.projectService.getAllProjects(this.companyId, this.teamId).subscribe({
      next: (projects) => {
        // Filter out inactive projects for non-admin users
        if (this.isAdmin) {
          this.projects = projects;
        } else {
          this.projects = projects.filter(project => project.active);
        }
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Failed to load projects:', error);
        this.errorMessage = 'Failed to load projects. Please try again.';
        this.isLoading = false;
      },
    });
  }

  openCreateModal(): void {
    const dialogRef = this.dialog.open(CreateProjectModalComponent, {
      width: '500px',
      panelClass: 'custom-modal',
      data: {
        companyId: this.companyId,
        teamId: this.teamId,
        userId: this.userId
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadProjects();
      }
    });
  }

  openEditModal(project: ProjectDto): void {
    const dialogRef = this.dialog.open(EditProjectModalComponent, {
      width: '500px',
      panelClass: 'custom-modal',
      data: {
        companyId: this.companyId,
        teamId: this.teamId,
        project: project,
        userId: this.userId,
        isAdmin: this.isAdmin
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadProjects();
      }
    });
  }

  navigateTo(route: string): void {
    this.router.navigate([route]);
  }

  goBack(): void {
    this.router.navigate(['/teams']);
  }

  logout(): void {
    this.authService.logout().subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Logout failed:', error);
        // Clear local data and navigate anyway
        this.authService.clearUserData();
        this.router.navigate(['/']);
      }
    });
  }
}
