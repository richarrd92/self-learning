import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthCard } from '../../components/auth-card/auth-card';
import { environment } from '../../../environments/environment';
import { AuthService } from '../../../services/authentication/auth';

/**
 * Homepage component (standalone) that displays the landing page.
 *
 * Features:
 *  - Shows Mapbox static map.
 *  - Includes the AuthCard component for login/signup.
 *  - Redirects logged-in users automatically to the dashboard.
 */
@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [RouterModule, AuthCard],
  templateUrl: './homepage.html',
  styleUrl: './homepage.css',
})
export class Homepage implements OnInit {
  /** Mapbox static map URL for displaying the default map */
  mapboxUrl = `https://api.mapbox.com/styles/v1/mapbox/streets-v12/static/-76.6338,39.3082,13,0/1280x800?access_token=${environment.mapboxToken}`;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    // Redirect to dashboard if already logged in
    this.authService.isLoggedIn$.subscribe((loggedIn) => {
      if (loggedIn) {
        this.router.navigate(['/dashboard']); // redirect
      }
    });
  }
}
