import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-task',
  imports: [],
  templateUrl: './task.html',
  styleUrl: './task.css',
})
export class Task {
  @Input() id: number = 0;
  @Input() title: string = '';
  @Input() completed: boolean = false;
  @Output() completionChange = new EventEmitter<{ id: number; completed: boolean }>();

  toggleCompletion() {
    this.completed = !this.completed;
    this.completionChange.emit({ id: this.id, completed: this.completed });
  }
}
