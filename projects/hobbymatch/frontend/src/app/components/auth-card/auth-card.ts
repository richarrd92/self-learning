import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LoginRequest, LoginResponse } from '../../models/auth/login';
import { RegisterRequest, RegisterResponse } from '../../models/auth/register';
import { SidebarService } from '../../../services/sidebar';
import { AuthService } from '../../../services/authentication/auth';
import { environment } from '../../../environments/environment';

/**
 * AuthCard component (standalone) used for login and signup forms.
 *
 * Features:
 *  - Switches between login and signup modes.
 *  - Reactive forms with validation.
 *  - Handles API requests for login and registration.
 *  - Displays error messages on failed requests.
 *  - Updates AuthService and navigates to dashboard on success.
 *  - Reacts to logout events from SidebarService to reset the form.
 */
@Component({
  selector: 'app-auth-card',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './auth-card.html',
  styleUrl: './auth-card.css',
})
export class AuthCard implements OnInit {
  form: FormGroup; // Reactive form
  errorMessage: string = '';

  /** Mode toggle: false = signup, true = login */
  isLoginMode: boolean = false;

  constructor(
    private http: HttpClient,
    private router: Router,
    private formBuilder: FormBuilder,
    private sidebarService: SidebarService,
    private authService: AuthService
  ) {
    // Initialize mode based on login state
    this.isLoginMode = this.authService.isLoggedIn();
    this.form = this.createForm();

    // React to logout events to reset form and switch to login
    this.sidebarService.logout$.subscribe(() => {
      this.isLoginMode = true;
      this.form = this.createForm();
    });
  }

  ngOnInit() {
    // Update mode reactively based on login state
    this.authService.isLoggedIn$.subscribe((loggedIn) => {
      this.isLoginMode = loggedIn;
      this.form = this.createForm(); // reset form fields
    });
  }

  /** Create form dynamically based on current mode */
  private createForm(): FormGroup {
    if (this.isLoginMode) {
      return this.formBuilder.group({
        username: ['', Validators.required],
        password: ['', Validators.required],
      });
    } else {
      return this.formBuilder.group({
        name: ['', Validators.required],
        password: ['', Validators.required],
      });
    }
  }

  /** Toggle between login and signup modes */
  toggleMode() {
    this.isLoginMode = !this.isLoginMode;
    this.form = this.createForm();
  }

  /** Handle form submission for login or signup */
  onSubmit() {
    if (this.form.invalid) return;

    if (this.isLoginMode) {
      this.login();
    } else {
      this.signup();
    }
  }

  /** Perform login API request */
  private login() {
    const payload: LoginRequest = {
      username: this.form.value.username,
      password: this.form.value.password,
    };

    // Make API request
    this.http.post<LoginResponse>(`${environment.apiBaseUrl}auth/login`, payload).subscribe({
      next: (res) => {
        this.authService.setLoggedIn(res.token);
        this.errorMessage = '';
        this.form.reset();
        this.router.navigate(['/dashboard']);
      },
      error: (err) => this.handleError(err),
    });
  }

  /** Perform signup API request */
  private signup() {
    const payload: RegisterRequest = {
      name: this.form.value.name,
      password: this.form.value.password,
    };

    // Make API request
    this.http.post<RegisterResponse>(`${environment.apiBaseUrl}auth/register`, payload).subscribe({
      next: (res) => {
        this.authService.setLoggedIn(res.token);
        this.errorMessage = '';
        this.form.reset();
        this.router.navigate(['/dashboard']);
      },
      error: (err) => this.handleError(err),
    });
  }

  /** Handle and display API errors */
  private handleError(err: any) {
    console.error(err);
    if (err.error?.message) {
      this.errorMessage = err.error.message;
    } else if (err.status === 0) {
      this.errorMessage = 'Cannot connect to server. Please try again later.';
    } else {
      this.errorMessage = 'An error occurred. Please check your input.';
    }
  }
}
