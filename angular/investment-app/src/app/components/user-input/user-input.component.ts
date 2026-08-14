import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InvestmentPayload } from '../../payload';

@Component({
  selector: 'app-user-input',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './user-input.component.html',
  styleUrl: './user-input.component.css',
})
export class UserInputComponent {
  // need to send this data to parenet component
  @Output() investmentData = new EventEmitter<InvestmentPayload>();

  enteredInitialInvestment: string = '20';
  enteredAnnualInvestment: string = '34';
  enteredExpectedReturn: string = '5';
  enteredDuration: string = '10';

  onSubmit() {
    const payload: InvestmentPayload = {
      initialInvestment: parseFloat(this.enteredInitialInvestment),
      annualInvestment: parseFloat(this.enteredAnnualInvestment),
      expectedReturn: parseFloat(this.enteredExpectedReturn),
      duration: parseInt(this.enteredDuration, 10),
    };
    console.log("form submitted with payload:", payload);
    this.investmentData.emit(payload);
  }
}
