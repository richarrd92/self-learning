import { Component, ElementRef, input, output, ViewChild } from '@angular/core';
import { ButtonComponent } from '../../../shared/button/button.component';
import { ControlComponent } from '../../../shared/control/control.component';
import { FormsModule } from '@angular/forms';
import { Ticket } from '../ticket.model';

@Component({
  selector: 'app-new-ticket',
  standalone: true,
  imports: [ButtonComponent, ControlComponent, FormsModule],
  templateUrl: './new-ticket.component.html',
  styleUrl: './new-ticket.component.css',
})
export class NewTicketComponent {
  @ViewChild('newTicketForm') newTicketForm?: ElementRef<HTMLFormElement>;
  submittedTicket?: { title: string; request: string };

  addTicket = output<Ticket>();

  onSubmit(title: HTMLInputElement, request: HTMLTextAreaElement) {
    // this.submittedTicket = {
    //   title: title.value,
    //   request: request.value
    // }

    this.addTicket.emit({
      id: Math.random().toString(),
      title: title.value,
      request: request.value,
      status: 'open',
    });

    // console.log(this.submittedTicket.title);
    // console.log(this.submittedTicket.request);
    this.newTicketForm?.nativeElement.reset();
  }
}
