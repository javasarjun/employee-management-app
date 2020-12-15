import { importsRoot } from './../../../app.imports';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeGetComponent } from './employee-get.component';

describe('EmployeeGetComponent', () => {
  let component: EmployeeGetComponent;
  let fixture: ComponentFixture<EmployeeGetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: importsRoot,
      declarations: [EmployeeGetComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeGetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
