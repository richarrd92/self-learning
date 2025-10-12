import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CounterBoard } from './counter-board';

describe('CounterBoard', () => {
  let component: CounterBoard;
  let fixture: ComponentFixture<CounterBoard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CounterBoard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CounterBoard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
