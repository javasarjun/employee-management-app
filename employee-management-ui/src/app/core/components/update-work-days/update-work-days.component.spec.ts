import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { UpdateWorkDaysComponent } from './update-work-days.component';

describe('UpdateWorkDaysComponent', () => {
  let component: UpdateWorkDaysComponent;
  let fixture: ComponentFixture<UpdateWorkDaysComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateWorkDaysComponent],
      providers: [
        { provide: MAT_DIALOG_DATA, useValue: {} },
        { provide: MatDialogRef, useValue: {} },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateWorkDaysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
