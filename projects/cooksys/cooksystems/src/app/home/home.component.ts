import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatButtonModule],
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {
  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    // If user is already logged in, redirect to select company page
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['/select-company']);
    }
  }

  navigateToLogin() {
    this.router.navigate(['/login']);
  }
}
