import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  protected readonly title = signal('soccerhub');

  teams = signal([
    { id: 1, name: 'Arsenal' },
    { id: 2, name: 'Chelsea' },
    { id: 3, name: 'Liverpool' },
  ]);
}
