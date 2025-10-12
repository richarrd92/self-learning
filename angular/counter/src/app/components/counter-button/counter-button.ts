import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-counter-button',
  imports: [],
  templateUrl: './counter-button.html',
  styleUrl: './counter-button.css'
})
export class CounterButton {
  @Input() bgColor: string = 'gray';
  @Input() label: string = 'Button';
  @Input() textColor: string = 'white';
}
