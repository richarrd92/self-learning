import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CompanyService, CompanyDto } from '../../services/company.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navbar.component.html'
})
export class NavbarComponent implements OnInit {
  username: string = '';
  isAdmin: boolean = false;
  companyId: number = 1;
  companyName: string = '';

  constructor(
    private router: Router,
    private authService: AuthService,
    private companyService: CompanyService
  ) {}

  ngOnInit(): void {
    const userData = this.authService.getUserData();
    if (userData) {
      this.username = userData.username;
      this.isAdmin = userData.admin;
    }

    const storedCompanyId = localStorage.getItem('selectedCompanyId');
    if (storedCompanyId) {
      this.companyId = +storedCompanyId;
    }

    this.loadCompanyName();
  }

  private loadCompanyName(): void {
    this.companyService.getAllCompanies().subscribe({
      next: (companies: CompanyDto[]) => {
        const company = companies.find(c => c.id === this.companyId);
        if (company) {
          this.companyName = company.name;
        }
      },
      error: (error) => {
        console.error('Failed to load company:', error);
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
        this.authService.clearUserData();
        this.router.navigate(['/']);
      }
    });
  }
}
