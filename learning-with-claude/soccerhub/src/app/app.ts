import { Component, computed, inject, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TeamService } from './team/team-service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App implements OnInit {
  protected readonly title = signal('Soccerhub');

  private teamService = inject(TeamService);
  protected teams = this.teamService.getTeams();
  protected status = this.teamService.getStatus();

  // Stored state: the two things we can't work out from anything else
  private readonly _userInput = signal(''); // raw text, exactly as typed
  private readonly inputEntered = signal(false); // has the user touched the box since the last reset?

  // Derived state: never set by hand
  protected readonly isBlank = computed(() => this._userInput().trim() === '');
  protected readonly showBlankError = computed(() => this.inputEntered() && this.isBlank());
  protected readonly canAdd = computed(() => this.status() !== 'loading' && !this.isBlank());

  ngOnInit(): void {
    this.teamService.loadTeams();
  }

  onTeamNameInput(input: HTMLInputElement): void {
    this._userInput.set(input.value);
    this.inputEntered.set(true);
  }

  addTeam(input: HTMLInputElement): void {
    const teamName = this._userInput().trim();
    if (!teamName) return;
    this.teamService.addTeam({ id: Date.now(), name: teamName });

    this._userInput.set('');
    this.inputEntered.set(false); // back to a fresh form
    input.value = ''; // the DOM doesn't follow the signal, so clear it too
  }
}
