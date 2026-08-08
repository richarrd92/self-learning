import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { UserResponse } from '../../app/models/user/UserResponse';
import { UserService } from '../user';

/**
 * Service for authentication and user session management.
 * 
 * Responsibilities:
 *  - Tracks login state (logged in/out).
 *  - Stores current user info.
 *  - Provides observables and synchronous getters for reactive and immediate access.
 *  - Handles login, logout, and token persistence.
 */
@Injectable({ providedIn: 'root' })
export class AuthService {

  /** Observable tracking whether the user is logged in */
  private loggedInSubject = new BehaviorSubject<boolean>(!!localStorage.getItem('token'));
  isLoggedIn$: Observable<boolean> = this.loggedInSubject.asObservable();

  /** Observable holding the current logged-in user info */
  private currentUserSubject = new BehaviorSubject<UserResponse | null>(null);
  currentUser$: Observable<UserResponse | null> = this.currentUserSubject.asObservable();

  constructor(private userService: UserService) {
    // Listen for token changes in localStorage across browser tabs
    window.addEventListener('storage', (event) => {
      if (event.key === 'token') {
        const token = localStorage.getItem('token');
        this.loggedInSubject.next(!!token);

        if (token) {
          this.fetchCurrentUser().subscribe(); // fetch current user
        } else {
          this.currentUserSubject.next(null); // clear current user
        }
      }
    });

    // Fetch current user on service init if token exists
    const token = localStorage.getItem('token');
    if (token) {
      this.fetchCurrentUser().subscribe();
    }
  }

  /**
   * Marks user as logged in.
   * Stores token in localStorage, updates login state,
   * and fetches current user info from API.
   */
  setLoggedIn(token: string) {
    localStorage.setItem('token', token);
    this.loggedInSubject.next(true);
    this.fetchCurrentUser().subscribe();
  }

  /**
   * Marks user as logged out.
   * Clears token, updates login state, and clears current user info.
   */
  setLoggedOut() {
    localStorage.removeItem('token');
    this.loggedInSubject.next(false);
    this.currentUserSubject.next(null);
  }

  // Get token safely
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  // Check login state synchronously
  isLoggedIn(): boolean {
    return this.loggedInSubject.value;
  }

  // Get current user synchronously
  getCurrentUserSync(): UserResponse | null {
    return this.currentUserSubject.value;
  }

  /**
   * Fetches the current user from the backend API.
   * Updates currentUserSubject with the fetched user.
   * Handles errors gracefully without throwing.
   */
  fetchCurrentUser() {
    const token = this.getToken();
    if (!token) return of(null); // no token

    return this.userService.getCurrentUser().pipe(
      tap((user) => this.currentUserSubject.next(user)), // update current user
      catchError((err) => {
        // logout user and redirect-
        console.warn('Failed to fetch current user', err);
        return of(null);
      })
    );
  }
}
