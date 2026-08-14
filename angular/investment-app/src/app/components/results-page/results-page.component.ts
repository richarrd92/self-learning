import { Component, input } from '@angular/core';
import { InvestmentResult } from '../../payload';
import { CommonModule } from '@angular/common';
import { MatPaginatorModule } from '@angular/material/paginator';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-results-page',
  standalone: true,
  imports: [CommonModule, MatPaginatorModule],
  templateUrl: './results-page.component.html',
  styleUrl: './results-page.component.css'
})
export class ResultsPageComponent {
  // pagination properties
  pageSize = 5
  pageIndex = 0

  results = input<InvestmentResult[]>([]);


  onPageChange(event: PageEvent) {
    this.pageSize = event.pageSize;
    this.pageIndex = event.pageIndex;
  }

  getPaginatedResults(): InvestmentResult[] {
    const start = this.pageIndex * this.pageSize;
    const end = start + this.pageSize;
    return this.results().slice(start, end);
  }
}
