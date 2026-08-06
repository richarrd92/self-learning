import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-first-child',
  imports: [],
  templateUrl: './first-child.html',
  styleUrl: './first-child.css',
})
export class FirstChild {
  @Output() firstChildEvent = new EventEmitter<string>();
  @Input() dataFromParent: string = '';

  onFirstChildClick() {
    this.firstChildEvent.emit('this is child 1 data');
  }
}
