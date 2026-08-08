import { Component, OnInit } from '@angular/core';
import { MapService } from '../../../services/map';
import { UserService } from '../../../services/user';
import { UserResponse } from '../../models/user/UserResponse';
import { Sidebar } from '../../components/sidebar/sidebar';
import { Navbar } from '../../components/navbar/navbar';
import { SidebarService } from '../../../services/sidebar';
import { AuthService } from '../../../services/authentication/auth';
import { Footer } from '../../components/footer/footer';
import { Router } from '@angular/router';

/**
 * Dashboard component (standalone) that displays the main user interface after login.
 *
 * Features:
 *  - Shows the sidebar, navbar, and footer components.
 *  - Reactively updates sidebar labels using SidebarService.
 *  - Fetches the current user and displays their information.
 *  - Initializes a Leaflet map centered on the user’s location.
 *  - Adds markers for all users on the map.
 */
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [Sidebar, Navbar, Footer],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  currentUser?: UserResponse; // Current logged-in user
  activeSidebarLabel: string = ''; // Active sidebar label

  constructor(
    private mapService: MapService,
    private userService: UserService,
    private sidebarService: SidebarService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    // Reactively update sidebar label
    this.sidebarService.label$.subscribe((label) => {
      this.activeSidebarLabel = label;
      console.log('Sidebar label updated in Dashboard:', label);
    });

    // Fetch current user and initialize map
    this.authService.fetchCurrentUser().subscribe((user) => {
      if (!user) return; // Redirect handled in AuthService

      this.currentUser = user;

      // Set map center to user location (default if missing)
      const center: [number, number] = [
        user.embeddedLocation?.latitude || 39.3082,
        user.embeddedLocation?.longitude || -76.6338,
      ];
      this.mapService.initMap('map', center);

      // Fetch all users and add markers to map
      const token = this.authService.getToken();
      if (!token) return;

      this.userService.getAllUsers().subscribe({
        next: (users) => this.mapService.addUserMarkers(users),
        error: (err) => console.error('Failed to load users', err),
      });
    });
  }
}
