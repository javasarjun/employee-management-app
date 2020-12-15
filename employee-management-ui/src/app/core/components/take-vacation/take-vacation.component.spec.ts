import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { TakeVacationComponent } from './take-vacation.component';

describe('TakeVacationComponent', () => {
  let component: TakeVacationComponent;
  let fixture: ComponentFixture<TakeVacationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TakeVacationComponent],
      providers: [
        { provide: MAT_DIALOG_DATA, useValue: {} },
        { provide: MatDialogRef, useValue: {} },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TakeVacationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
