import { Component, signal } from '@angular/core';
import { UserInputComponent } from './components/user-input/user-input.component';
import { HeaderComponent } from './components/header/header.component';
import { InvestmentPayload } from './payload';
import { ResultsPageComponent } from './components/results-page/results-page.component';
import { InvestmentResult } from './payload';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  imports: [HeaderComponent, UserInputComponent, ResultsPageComponent],
})
export class AppComponent {}
