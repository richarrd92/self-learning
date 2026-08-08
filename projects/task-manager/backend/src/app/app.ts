import { Component } from '@angular/core';
import { Tasks } from '../app/tasks/tasks';
@Component({
  selector: 'app-root',
  imports: [Tasks],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
}
