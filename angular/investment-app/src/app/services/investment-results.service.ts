import { Injectable, signal } from '@angular/core';
import { InvestmentResult, InvestmentPayload } from '../payload';

@Injectable({
  providedIn: 'root',
})
export class InvestmentResultsService {
  results?: {
    year: number;
    interest: number;
    valueEndOfYear: number;
    annualInvestment: number;
    totalInterest: number;
    totalAmountInvested: number;
  }[];

  calculateInvestmentResults(payload: InvestmentPayload): void {
    const { initialInvestment, annualInvestment, expectedReturn, duration } =
      payload;

    const annualData = [];
    let investmentValue = initialInvestment;

    for (let i = 0; i < duration; i++) {
      const year = i + 1;
      const interestEarnedInYear = investmentValue * (expectedReturn / 100);
      investmentValue += interestEarnedInYear + annualInvestment;
      const totalInterest =
        investmentValue - annualInvestment * year - initialInvestment;
      annualData.push({
        year: year,
        interest: +interestEarnedInYear,
        valueEndOfYear: +investmentValue,
        annualInvestment: +annualInvestment,
        totalInterest: +totalInterest,
        totalAmountInvested: +(initialInvestment + annualInvestment * year),
      });
    }

    this.results = annualData;
  }
}
