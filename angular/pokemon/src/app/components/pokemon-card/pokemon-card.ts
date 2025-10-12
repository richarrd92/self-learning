import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-pokemon-card',
  imports: [ CommonModule, FormsModule ],
  templateUrl: './pokemon-card.html',
  styleUrl: './pokemon-card.css'
})
export class PokemonCard {
  @Input() pokemon: any;
}
