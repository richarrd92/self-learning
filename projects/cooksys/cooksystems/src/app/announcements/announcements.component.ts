import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { AnnouncementService, AnnouncementDto } from '../services/announcement.service';
import { CreateAnnouncementModalComponent } from './create-announcement-modal/create-announcement-modal.component';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { NavbarComponent } from '../shared/navbar/navbar.component';

@Component({
  selector: 'app-announcements',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatDialogModule, NavbarComponent],
  templateUrl: './announcements.component.html',
})
export class AnnouncementsComponent implements OnInit {
  announcements: AnnouncementDto[] = [];
  isLoading: boolean = true;
  errorMessage: string = '';
  isAdmin: boolean = false;
  username: string = '';
  companyId: number = 1; // TODO: Get this from route params or local storage
  userId: number = 1; // TODO: Get this from auth service

  constructor(
    private announcementService: AnnouncementService,
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

    this.loadAnnouncements();
  }

  loadAnnouncements(): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.announcementService.getAllAnnouncements(this.companyId).subscribe({
      next: (announcements) => {
        this.announcements = announcements;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Failed to load announcements:', error);
        this.errorMessage = 'Failed to load announcements. Please try again.';
        this.isLoading = false;
      },
    });
  }

  openCreateModal(): void {
    const dialogRef = this.dialog.open(CreateAnnouncementModalComponent, {
      width: '500px',
      panelClass: 'custom-modal',
      data: {
        companyId: this.companyId,
        userId: this.userId,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loadAnnouncements();
      }
    });
  }

  navigateTo(route: string): void {
    this.router.navigate([route]);
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
      },
    });
  }
}
