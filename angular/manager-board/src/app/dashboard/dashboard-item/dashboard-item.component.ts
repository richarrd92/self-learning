import { Component, input } from '@angular/core';

@Component({
  selector: 'app-dashboard-item',
  standalone: true,
  imports: [],
  templateUrl: './dashboard-item.component.html',
  styleUrl: './dashboard-item.component.css'
})
export class DashboardItemComponent {
  /**
   * input function?
   * because this wrapper component expects to receive these values
   * input in angular world means "hey this value will come from somewhere else"
   */
  image = input.required<{ src: string; alt: string }>();
  title = input.required<string>();
}
