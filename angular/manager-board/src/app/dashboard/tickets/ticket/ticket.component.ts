import { Component, input, output, signal } from '@angular/core';
import { Ticket } from '../ticket.model';

@Component({
  selector: 'app-ticket',
  standalone: true,
  imports: [],
  templateUrl: './ticket.component.html',
  styleUrl: './ticket.component.css'
})
export class TicketComponent {
  data = input.required<Ticket>()
  closeTicket = output<string>()
  dataVisible = signal(false);

  toggleData() {
    // this.dataVisible.set(!this.dataVisible());
    this.dataVisible.update((dataWasVisible) => !dataWasVisible);
  }

  completeTicket() {
    console.log('complete ticket');
    this.closeTicket.emit(this.data().id);
  }
}
