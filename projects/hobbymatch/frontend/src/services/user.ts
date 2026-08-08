import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserResponse } from '../app/models/user/UserResponse';
import { environment } from '../environments/environment';

/**
 * Service for interacting with the backend API for user-related operations.
 *
 * Provides methods to:
 *  - Fetch the currently logged-in user.
 *  - Fetch a list of all users.
 *
 * This service uses Angular's HttpClient to make asynchronous HTTP requests
 * and returns results as Observables for reactive handling.
 *
 * Notes:
 *  - All API URLs are built from the environment's apiBaseUrl.
 *  - Observables allow components to reactively subscribe to data changes.
 */
@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = `${environment.apiBaseUrl}users`;

  constructor(private http: HttpClient) {}

  /**
   * Fetches the currently logged-in user's data.
   * @returns An Observable of type UserResponse (asynchronous user data)
   * Example backend endpoint: GET /api/users/me
   */
  getCurrentUser(): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.baseUrl}/me`);
  }

  /**
   * Fetches all users from the backend.
   * @returns An Observable array of UserResponse objects
   * Example backend endpoint: GET /api/users
   */
  getAllUsers(): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(`${this.baseUrl}`);
  }
}
