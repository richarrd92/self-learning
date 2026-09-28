import { Injectable, signal } from '@angular/core';
import type { Team } from './team.model';

@Injectable({
  providedIn: 'root',
})
export class TeamService {
  private teams = signal<Team[]>([
    { id: 1, name: 'Arsenal' },
    { id: 2, name: 'Chelsea' },
    { id: 3, name: 'Liverpool' },
  ]);

  getTeams() {
    return this.teams.asReadonly();
  }

  addTeam(team: Team): void {
    const newTeams = [...this.teams(), team];
    this.teams.set(newTeams);
  }
}
