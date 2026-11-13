import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable, tap } from 'rxjs';

export interface LoginRequest {
  username?: string;
  email?: string;
  password: string;
}

export interface LoginResponse {
  id: number;
  username: string;
  email: string;
  admin: boolean;
  companyId?: number; // Optional: Company ID for non-admin users
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly USER_DATA_KEY = 'userData';

  constructor(private apiService: ApiService) {}

  login(credentials: LoginRequest): Observable<LoginResponse> {
    return this.apiService.post<LoginResponse>('/auth/login', credentials).pipe(
      tap(response => {
        this.saveUserData(response);
      })
    );
  }

  logout(): Observable<any> {
    return this.apiService.post('/auth/logout', {}).pipe(
      tap(() => {
        this.clearUserData();
      })
    );
  }

  private saveUserData(data: LoginResponse): void {
    localStorage.setItem(this.USER_DATA_KEY, JSON.stringify(data));
  }

  getUserData(): LoginResponse | null {
    const data = localStorage.getItem(this.USER_DATA_KEY);
    return data ? JSON.parse(data) : null;
  }

  clearUserData(): void {
    localStorage.removeItem(this.USER_DATA_KEY);
  }

  isLoggedIn(): boolean {
    return this.getUserData() !== null;
  }

  isAdmin(): boolean {
    const userData = this.getUserData();
    return userData ? userData.admin : false;
  }
}
