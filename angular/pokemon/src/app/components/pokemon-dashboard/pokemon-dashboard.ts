import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PokemonCard } from '../pokemon-card/pokemon-card';

@Component({
  selector: 'app-pokemon-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule, PokemonCard],
  templateUrl: './pokemon-dashboard.html',
  styleUrl: './pokemon-dashboard.css',
})
export class PokemonDashboard {
  pokemonName = ''; // bound to the input
  pokemon: any = null;
  loading = false;
  error = '';

  constructor(private http: HttpClient) {}

  fetchPokemon() {
    if (!this.pokemonName) return;

    this.loading = true;
    this.error = '';
    this.pokemon = null;

    const url = `https://pokeapi.co/api/v2/pokemon/${this.pokemonName.toLowerCase()}`;
    this.http.get(url).subscribe({
      next: (data) => {
        this.pokemon = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'Pokémon not found!';
        this.loading = false;
      },
    });
  }
}
