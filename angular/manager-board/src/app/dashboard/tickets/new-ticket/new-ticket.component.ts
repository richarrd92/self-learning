import { Component, ElementRef, ViewChild } from '@angular/core';
import { ButtonComponent } from "../../../shared/button/button.component";
import { ControlComponent } from '../../../shared/control/control.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-new-ticket',
  standalone: true,
  imports: [ButtonComponent, ControlComponent, FormsModule],
  templateUrl: './new-ticket.component.html',
  styleUrl: './new-ticket.component.css',
})
export class NewTicketComponent {
  @ViewChild('newTicketForm') newTicketForm?: ElementRef<HTMLFormElement>;
  submittedTicket?: { title: string, request: string };

  onSubmit(title: HTMLInputElement, request: HTMLTextAreaElement) {
    this.submittedTicket = {
      title: title.value,
      request: request.value
    }

    console.log(this.submittedTicket.title);
    console.log(this.submittedTicket.request);
    this.newTicketForm?.nativeElement.reset();
  }
}
