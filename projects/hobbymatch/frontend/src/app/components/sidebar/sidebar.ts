import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router, NavigationEnd, RouterModule } from '@angular/router';
import { filter } from 'rxjs';
import { UserResponse } from '../../models/user/UserResponse';
import { SIDEBAR_CONFIG, SidebarButton } from '../../models/sidebar';
import { CommonModule } from '@angular/common';
import { SidebarService } from '../../../services/sidebar';
import { AuthService } from '../../../services/authentication/auth';

/**
 * Sidebar component (standalone) that displays navigation buttons for different sections.
 *
 * Features:
 *  - Dynamically updates buttons based on current route.
 *  - Highlights active button.
 *  - Emits selected label and updates SidebarService.
 *  - Handles logout functionality.
 *
 * Inputs:
 *  - currentUser: Optional UserResponse to show user-related data.
 * 
 * Outputs:
 *  - label: Emits the currently active sidebar label.
 */
@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css',
})
export class Sidebar implements OnInit {
  @Input() currentUser?: UserResponse;
  @Output() label = new EventEmitter<string>();

  /** Buttons displayed in the sidebar for the current route */
  buttons: SidebarButton[] = [];

  constructor(
    public router: Router,
    private sidebarService: SidebarService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.updateButtonsForRoute(this.router.url);

    // Update buttons on route change
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event: any) => this.updateButtonsForRoute(event.urlAfterRedirects));
  }

  /** Update buttons array and active label based on current route */
  private updateButtonsForRoute(route: string) {
    const pageKey = route.split('/')[1] || 'dashboard';
    this.buttons = SIDEBAR_CONFIG[pageKey] || [];

    const active = this.buttons.find((btn) => route.startsWith(btn.route));
    if (active) this.sidebarService.setLabel(active.label);
  }

  /** Checks if a route is active */
  isActive(route: string): boolean {
    return this.router.url.startsWith(route);
  }

  /** Logs out the user, resets sidebar, and navigates to home */
  logout(event?: Event) {
    if (event) event.preventDefault();
    this.authService.setLoggedOut(); // update AuthService
    this.sidebarService.setLabel(''); // Reset label
    this.router.navigate(['/']);
  }

  /** Handles sidebar button click: updates label and navigates */
  onButtonClick(event: Event, button: SidebarButton) {
    event.preventDefault(); // prevent default <a> behavior
    this.sidebarService.setLabel(button.label); // update service
    this.router.navigate([button.route]); // navigate
    console.log('Button clicked:', button.label);
  }
}
