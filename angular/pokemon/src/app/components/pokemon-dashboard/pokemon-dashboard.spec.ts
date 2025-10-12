import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokemonDashboard } from './pokemon-dashboard';

describe('PokemonDashboard', () => {
  let component: PokemonDashboard;
  let fixture: ComponentFixture<PokemonDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PokemonDashboard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PokemonDashboard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
