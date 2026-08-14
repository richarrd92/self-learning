import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InvestmentPayload } from '../../payload';
import { InvestmentResultsService } from '../../services/investment-results.service';

@Component({
  selector: 'app-user-input',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './user-input.component.html',
  styleUrl: './user-input.component.css',
})
export class UserInputComponent {

  constructor(private investmentResultsService: InvestmentResultsService) { }

  enteredInitialInvestment: string = '10';
  enteredAnnualInvestment: string = '20';
  enteredExpectedReturn: string = '5';
  enteredDuration: string = '10';

  onSubmit() {
    this.investmentResultsService.calculateInvestmentResults({
      initialInvestment: parseFloat(this.enteredInitialInvestment),
      annualInvestment: parseFloat(this.enteredAnnualInvestment),
      expectedReturn: parseFloat(this.enteredExpectedReturn),
      duration: parseInt(this.enteredDuration, 10),
    });
  }
}
