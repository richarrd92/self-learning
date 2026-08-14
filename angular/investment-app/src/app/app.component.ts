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
export class AppComponent {
  results = signal<InvestmentResult[]>([]);

  calculateInvestmentResults(payload: InvestmentPayload): void {
    const annualData: InvestmentResult[] = [];
    let investmentValue = payload.initialInvestment;

    for (let i = 0; i < payload.duration; i++) {
      const year = i + 1;
      const interestEarnedInYear =
        investmentValue * (payload.expectedReturn / 100);
      investmentValue += interestEarnedInYear + payload.annualInvestment;
      const totalInterest =
        investmentValue -
        payload.annualInvestment * year -
        payload.initialInvestment;
      annualData.push({
        year: year,
        interest: +interestEarnedInYear.toFixed(2),
        valueEndOfYear: +investmentValue.toFixed(2),
        annualInvestment: payload.annualInvestment,
        totalInterest: +totalInterest.toFixed(2),
        totalAmountInvested: +(
          payload.initialInvestment +
          payload.annualInvestment * year
        ).toFixed(2),
      });
    }

    this.results.set(annualData);
  }
}
