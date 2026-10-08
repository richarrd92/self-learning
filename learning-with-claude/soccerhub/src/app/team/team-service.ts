import { Injectable, signal } from '@angular/core';
import type { Team } from './team.model';
type LoadingState = 'loading' | 'loaded' | 'error';

@Injectable({
  providedIn: 'root',
})
export class TeamService {
  private teams = signal<Team[]>([
    { id: 1, name: 'Arsenal' },
    { id: 2, name: 'Chelsea' },
    { id: 3, name: 'Liverpool' },
  ]);

  private _status = signal<LoadingState>('loading');
  private status = this._status;

  getTeams() {
    return this.teams.asReadonly();
  }

  addTeam(team: Team): void {
    setTimeout(() => {
      const newTeams = [...this.teams(), team];
      this.teams.set(newTeams);
    }, 1000);
  }

  getStatus() {
    return this.status.asReadonly();
  }

  setStatus(status: LoadingState) {
    this._status.set(status);
  }

  // Load teams from the server (simulated with a timeout)
  loadTeams(): void {
    setTimeout(() => {
      this.setStatus('loaded');
    }, 3000);
  }
}
