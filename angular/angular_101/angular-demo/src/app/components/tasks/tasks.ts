import { Component, signal, TrackByFunction } from '@angular/core';
import { Task } from '../task/task';
import { CommonModule } from '@angular/common';
import { myTasks } from './myTasks';

@Component({
  selector: 'app-tasks',
  imports: [Task, CommonModule],
  templateUrl: './tasks.html',
  styleUrl: './tasks.css',
})
export class Tasks {
  tasks = myTasks;

  getTasks() {
    return this.tasks;
  }

  onToggleComplete(id: number) {
    const task = this.tasks.find((t) => t.id === id);
    if (task) {
      task.complete = !task.complete;
    }
  }

  deleteTask(id: number) {
    const toDeleteTask = this.tasks.find((t) => t.id === id);

    // Check if there task with id exists and if it is complete before deleting
    if (toDeleteTask) {
      if (toDeleteTask.complete) {
        if (confirm('Are you sure you want to delete this task?')) {
          this.tasks = this.tasks.filter((t) => t.id !== id);
        }
      } else {
        alert('You can only delete completed tasks.');
      }
    }
  }
}
