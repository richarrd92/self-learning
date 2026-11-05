import { Injectable, signal, Signal } from '@angular/core';
import type { Team } from './team.model';
import { teams } from './team.model';

type LoadingState = 'loading' | 'loaded' | 'error';

@Injectable({
  providedIn: 'root',
})
export class TeamService {
  private teams = signal<Team[]>(teams);
  private _status = signal<LoadingState>('loading');
  private loadTimer: ReturnType<typeof setTimeout> | undefined;

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
    return this._status.asReadonly();
  }

  // Load teams from the server (simulated with a timeout)
  loadTeams(shouldFail: boolean): void {
    clearTimeout(this.loadTimer);
    this._status.set('loading');
    this.loadTimer = setTimeout(() => {
      this._status.set(shouldFail ? 'error' : 'loaded');
    }, 3000);
  }
}
