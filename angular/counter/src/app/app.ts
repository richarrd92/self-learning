import { Component, signal } from '@angular/core';
import { CounterBoard } from './components/counter-board/counter-board';

@Component({
  selector: 'app-root',
  imports: [CounterBoard],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('counter');
}
