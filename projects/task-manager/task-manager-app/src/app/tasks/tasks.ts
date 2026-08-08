import { Component } from '@angular/core';
import { Task } from '../../models/task';
import { dummyTasks } from '../../utils/dummy-tasks';
import { CommonModule } from '@angular/common';
import { Task as TaskComponent } from './task/task';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-tasks',
  imports: [CommonModule, TaskComponent, FormsModule],
  templateUrl: './tasks.html',
  styleUrl: './tasks.css',
})
export class Tasks {
  newTaskTitle: string = '';
  
  tasks: Task[] = dummyTasks;
  totalTasks: number = this.tasks.length;
  completedTasks: number = this.tasks.filter(task => task.completed).length;

  addTask() {
    if (this.newTaskTitle.trim() !== '') {
      const newTask: Task = {
        id: this.tasks.length + 1,
        title: this.newTaskTitle,
        completed: false,
      };
      this.tasks.push(newTask);
      this.newTaskTitle = '';
      this.totalTasks = this.tasks.length;
      this.completedTasks = this.tasks.filter(task => task.completed).length;
    }
  }

  onTaskCompletion(event: { id: number; completed: boolean }) {
    const task = this.tasks.find(item => item.id === event.id);
    if (task) {
      task.completed = event.completed;
    }

    this.totalTasks = this.tasks.length;
    this.completedTasks = this.tasks.filter(task => task.completed).length;
  }
}