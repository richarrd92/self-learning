import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { TeamService, TeamDto } from '../services/team.service';
import { CreateTeamModalComponent } from './create-team-modal/create-team-modal';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { NavbarComponent } from '../shared/navbar/navbar.component';

@Component({
  selector: 'app-teams',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatDialogModule, NavbarComponent],
  templateUrl: './teams.html',
  styleUrl: './teams.css',
})
export class Teams implements OnInit {
  teams: TeamDto[] = [];
  isLoading: boolean = true;
  errorMessage: string = '';
  isAdmin: boolean = false;
  username: string = '';
  companyId: number = 1; // Will be set from query params
  userId: number = 1; // Will be set from auth service

  constructor(
    private teamService: TeamService,
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

    // Get companyId from localStorage
    const storedCompanyId = localStorage.getItem('selectedCompanyId');
    if (storedCompanyId) {
      this.companyId = +storedCompanyId;
    }

    this.loadTeams();
  }

  loadTeams(): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.teamService.getAllTeams(this.companyId).subscribe({
      next: (teams) => {
        this.teams = teams;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Failed to load teams:', error);
        this.errorMessage = 'Failed to load teams. Please try again.';
        this.isLoading = false;
      },
    });
  }

  openCreateModal(): void {
    const dialogRef = this.dialog.open(CreateTeamModalComponent, {
      width: '500px',
      panelClass: 'custom-modal',
      data: {
        companyId: this.companyId,
        userId: this.userId
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadTeams();
      }
    });
  }

  navigateTo(route: string): void {
    this.router.navigate([route]);
  }

  navigateToProjects(teamId: number, teamName: string): void {
    // Store teamId and teamName in localStorage for projects page
    localStorage.setItem('selectedTeamId', teamId.toString());
    localStorage.setItem('selectedTeamName', teamName);
    this.router.navigate(['/projects']);
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
