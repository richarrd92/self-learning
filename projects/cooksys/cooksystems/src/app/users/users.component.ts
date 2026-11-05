import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { UserService, UserResponseDto } from '../services/user.service';
import { AddUserModalComponent } from './add-user-modal/add-user-modal.component';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { NavbarComponent } from '../shared/navbar/navbar.component';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatDialogModule, NavbarComponent],
  templateUrl: './users.component.html',
})
export class UsersComponent implements OnInit {
  users: UserResponseDto[] = [];
  isLoading: boolean = true;
  errorMessage: string = '';
  isAdmin: boolean = false;
  username: string = '';
  companyId: number = 1; // Will be set from query params

  constructor(
    private userService: UserService,
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
    }

    // Get companyId from localStorage
    const storedCompanyId = localStorage.getItem('selectedCompanyId');
    if (storedCompanyId) {
      this.companyId = +storedCompanyId;
    }

    this.loadUsers();
  }

  loadUsers(): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.userService.getUsersByCompany(this.companyId).subscribe({
      next: (users) => {
        this.users = users;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Failed to load users:', error);
        this.errorMessage = 'Failed to load users. Please try again.';
        this.isLoading = false;
      },
    });
  }

  openAddUserModal(): void {
    const dialogRef = this.dialog.open(AddUserModalComponent, {
      width: '500px',
      panelClass: 'custom-modal',
      data: {
        companyId: this.companyId,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loadUsers();
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
