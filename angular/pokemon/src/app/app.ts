import { Component } from '@angular/core';
import { PokemonDashboard } from "./components/pokemon-dashboard/pokemon-dashboard";

@Component({
  selector: 'app-root',
  imports: [PokemonDashboard],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
}
