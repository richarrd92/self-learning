import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserRepoCardComponent } from './user-repo-card.component';

describe('UserRepoCardComponent', () => {
  let component: UserRepoCardComponent;
  let fixture: ComponentFixture<UserRepoCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserRepoCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserRepoCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
