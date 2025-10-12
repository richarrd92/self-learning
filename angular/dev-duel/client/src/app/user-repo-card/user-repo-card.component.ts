import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-user-repo-card',
  templateUrl: './user-repo-card.component.html',
  styleUrls: ['./user-repo-card.component.css'],
})
export class UserRepoCardComponent implements OnInit {
  @Input() profile: any; // profile object from API
  @Input() isWinner: boolean = false; // highlight winner card
  @Input() isTie: boolean = false; // highlight tie card

  constructor() {}

  ngOnInit(): void {}
}
