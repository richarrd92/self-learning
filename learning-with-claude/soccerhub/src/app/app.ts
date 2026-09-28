import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TeamService } from './team/team-service';
import type { Team } from './team/team.model';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  protected readonly title = signal('soccerhub');

  private teamService = inject(TeamService);
  protected teams = this.teamService.getTeams();

  addTeam(input: HTMLInputElement): void {
    const teamName = input.value.trim();
    if (!teamName) return;
    this.teamService.addTeam({ id: Date.now(), name: teamName });
    input.value = '';
  }

  onTeamNameInput(): void {}
}
