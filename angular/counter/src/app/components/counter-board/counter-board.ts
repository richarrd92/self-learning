import { Component } from '@angular/core';
import { CounterDisplay } from '../counter-display/counter-display';
import { CounterButton } from "../counter-button/counter-button";

@Component({
  selector: 'app-counter-board',
  imports: [CounterDisplay, CounterButton],
  templateUrl: './counter-board.html',
  styleUrl: './counter-board.css',
})
export class CounterBoard {
  counter: number = 0;

  // Increment
  increment() {
    this.counter++;
  }

  // Decrement
  decrement() {
      if (this.counter > 0) {
        this.counter--;
      }
  }

  // Reset
  reset() {
    this.counter = 0;
  }
}
