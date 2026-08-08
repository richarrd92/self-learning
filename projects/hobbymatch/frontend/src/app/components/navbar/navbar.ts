import { Component, OnInit } from '@angular/core';
import { SidebarService } from '../../../services/sidebar';

/**
 * Navbar component (standalone) that displays the current active sidebar label.
 *
 * Features:
 *  - Subscribes to SidebarService to reactively display the active label.
 *  - Updates `activeLabel` whenever the sidebar selection changes.
 */
@Component({
  selector: 'app-navbar',
  imports: [],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar implements OnInit {
  /** Current active label from the sidebar */
  activeLabel: string = '';

  constructor(private SidebarService: SidebarService) {}

  ngOnInit() {
    // Subscribe to sidebar label changes
    this.SidebarService.label$.subscribe((label) => {
      this.activeLabel = label;
    });
  }
}
