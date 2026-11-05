import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CompanyService, CompanyDto } from '../services/company.service';

@Component({
  selector: 'app-select-company',
  standalone: true,
  imports: [CommonModule, MatSelectModule, MatFormFieldModule, MatButtonModule, FormsModule],
  templateUrl: './select-company.component.html',
})
export class SelectCompanyComponent implements OnInit {
  companies: CompanyDto[] = [];
  selectedCompanyId: number | null = null;
  isLoading: boolean = true;
  errorMessage: string = '';

  constructor(
    private companyService: CompanyService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadCompanies();
  }

  loadCompanies(): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.companyService.getAllCompanies().subscribe({
      next: (companies) => {
        this.companies = companies;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Failed to load companies:', error);
        this.errorMessage = 'Failed to load companies. Please try again.';
        this.isLoading = false;
      },
    });
  }

  onCompanySelect(): void {
    if (this.selectedCompanyId) {
      console.log('Selected company ID:', this.selectedCompanyId);
      // Store company ID in localStorage
      localStorage.setItem('selectedCompanyId', this.selectedCompanyId.toString());
      this.router.navigate(['/announcements']);
    }
  }
}
