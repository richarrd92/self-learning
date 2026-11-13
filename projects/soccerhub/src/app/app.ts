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

  protected readonly searchText = signal('');

  protected readonly visibleTeams = computed(() => {
    const query = this.searchText().trim().toLowerCase();
    return this.teams().filter((team) => team.name.toLowerCase().includes(query));
  });

  ngOnInit(): void {
    this.teamService.loadTeams(false); // load teams on startup, and succeed
  }

  reloadWithOppositeOutcome(): void {
    // toggle the failure state and load teams with the new state
    this.teamService.loadTeams(this.status() === 'loaded');
  }

  onTeamNameInput(input: HTMLInputElement): void {
    this._userInput.set(input.value);
    this.inputEntered.set(true);
  }

  onSearchTeamNameInput(input: HTMLInputElement): void {
    console.log(input.value);
    this.searchText.set(input.value);
  }

  // searchTeams(input: HTMLInputElement): void {
  //   const query = this._userInput().trim();
  //   if (!query) return;
  //   this.teams = this.teamService.searchTeams(query);
  // }

  addTeam(input: HTMLInputElement): void {
    const teamName = this._userInput().trim();
    if (!teamName) return;
    this.teamService.addTeam({ id: Date.now(), name: teamName });

    this._userInput.set('');
    this.inputEntered.set(false); // back to a fresh form
    input.value = ''; // the DOM doesn't follow the signal, so clear it too
  }
}
