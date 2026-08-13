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
  @Input() complete: boolean = false;


  @Output() toggleCompleteEvent = new EventEmitter<number>();
  @Output() deleteTaskEvent = new EventEmitter<number>();

  toggleComplete() {
    this.toggleCompleteEvent.emit(this.id);
  }

  deleteTask() {
    this.deleteTaskEvent.emit(this.id);
  }
}
