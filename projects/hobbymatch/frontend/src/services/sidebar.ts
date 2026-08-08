import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

/**
 * Service for managing sidebar state across the app.
 * 
 * Exposes reactive streams:
 *  - `label$`: current sidebar label.
 *  - `logout$`: emits when user logs out.
 *
 * Methods:
 *  - `setLabel(label: string)`: updates sidebar label.
 */
@Injectable({ providedIn: 'root' })
export class SidebarService {
  // Current sidebar label
  private labelSubject = new BehaviorSubject<string>('');
  label$ = this.labelSubject.asObservable();

  // Emits when the user logs out
  private logoutSubject = new BehaviorSubject<void>(undefined);
  logout$ = this.logoutSubject.asObservable();

  /**
   * Updates the sidebar label and notifies all subscribers.
   * @param label The new label string to display in the sidebar.
   */
  setLabel(label: string) {
    this.labelSubject.next(label);
  }
}
