import { Component, Input } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FirstChild } from '../components/first-child/first-child';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FirstChild],
  templateUrl: './app.html',
})
export class App {
  @Input() data: string = 'App component';
  firstChildData: string = '';
  data1: string = 'Data for FirstChild';

  onFirstChildClick(data: string) {
    this.firstChildData = data;
  }
}
