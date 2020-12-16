import { UpdateWorkDaysComponent } from './../update-work-days/update-work-days.component';
import { declarationsRoot } from './../../../app.declarations';
import { EmployeesService } from './../../services/employees.service';
import { importsRoot } from './../../../app.imports';
import {
  ComponentFixture,
  TestBed,
  fakeAsync,
  tick,
} from '@angular/core/testing';

import { EmployeeGetComponent } from './employee-get.component';
import Employee from '../../models/Employee';
import { Observable, Observer } from 'rxjs';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

describe('EmployeeGetComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: importsRoot,
      declarations: declarationsRoot,
      providers: [
        { provide: MAT_DIALOG_DATA, useValue: {} },
        { provide: MatDialogRef, useValue: {} },
      ],
    }).compileComponents();
  });

  describe(':', () => {
    function setup(): any {
      const fixture = TestBed.createComponent(EmployeeGetComponent);
      const fixture2 = TestBed.createComponent(UpdateWorkDaysComponent);
      const app = fixture.debugElement.componentInstance;
      const employeeService = fixture.debugElement.injector.get(
        EmployeesService
      );
      return { fixture, app, employeeService, fixture2 };
    }

    it('should create', () => {
      const { app } = setup();
      expect(app).toBeTruthy();
    });

    it('should load employee data', fakeAsync(() => {
      const { fixture, app, employeeService } = setup();

      const mockEmployeesData: Employee[] = [
        {
          id: '1',
          name: 'United states of america',
          daysWorked: 260,
          maxVacationDaysInWorkYear: 10,
          type: 'HOURLY',
          vacationDaysAccumulated: 10.0,
        },
      ];

      spyOn(employeeService, 'getEmployees').and.returnValue(
        new Observable((observer: Observer<Employee[]>) => {
          observer.next(mockEmployeesData);
          observer.complete();
        })
      );

      tick();

      fixture.detectChanges();

      const employeeAsyncElement = fixture.debugElement.nativeElement;
      const tableRows = employeeAsyncElement.querySelectorAll('tr');

      expect(tableRows.length).toBe(2);

      // data rows
      const headerRow = tableRows[1];

      expect(headerRow.cells[0].innerHTML).toBe('United states of america');
      expect(headerRow.cells[1].innerHTML).toBe('HOURLY');
      expect(headerRow.cells[2].innerHTML).toBe('10.0');
      expect(headerRow.cells[3].innerHTML).toBe('260');

      // two buttons should be present on actions column

      expect(headerRow.cells[4].querySelectorAll('button').length).toBe(2);
    }));

    it('should load table with "No data matching the filter" when no employee data present', fakeAsync(() => {
      const { fixture, employeeService } = setup();

      const mockEmployeesData: Employee[] = [];

      spyOn(employeeService, 'getEmployees').and.returnValue(
        new Observable((observer: Observer<Employee[]>) => {
          observer.next(mockEmployeesData);
          observer.complete();
        })
      );

      tick();

      fixture.detectChanges();

      const employeeAsyncElement = fixture.debugElement.nativeElement;
      const tableRows = employeeAsyncElement.querySelectorAll('tr');

      expect(tableRows[1].cells[0].innerHTML).toBe(
        'No data matching the filter'
      );
    }));

    it('should display the modal when `Update Work Days` is clicked', fakeAsync(() => {
      const { fixture, employeeService, app } = setup();

      const mockEmployeesData: Employee[] = [
        {
          id: '1',
          name: 'United states of america',
          daysWorked: 260,
          maxVacationDaysInWorkYear: 10,
          type: 'HOURLY',
          vacationDaysAccumulated: 10.0,
        },
      ];

      spyOn(employeeService, 'getEmployees').and.returnValue(
        new Observable((observer: Observer<Employee[]>) => {
          observer.next(mockEmployeesData);
          observer.complete();
        })
      );

      tick();

      fixture.detectChanges();

      let buttonDebugElements: DebugElement[] = fixture.debugElement.queryAll(
        By.css('button')
      );
      expect(buttonDebugElements.length).toEqual(4);
      expect(buttonDebugElements[0].nativeElement.innerText).toEqual(
        'Update Work Days'
      );

      // Open work days modal
      buttonDebugElements[0].triggerEventHandler('click', mockEmployeesData[0]);
      fixture.detectChanges();
    }));

    it('should display the modal when `Vacation Days` is clicked', fakeAsync(() => {
      const { fixture, employeeService, app } = setup();

      const mockEmployeesData: Employee[] = [
        {
          id: '1',
          name: 'United states of america',
          daysWorked: 260,
          maxVacationDaysInWorkYear: 10,
          type: 'HOURLY',
          vacationDaysAccumulated: 10.0,
        },
      ];

      spyOn(employeeService, 'getEmployees').and.returnValue(
        new Observable((observer: Observer<Employee[]>) => {
          observer.next(mockEmployeesData);
          observer.complete();
        })
      );

      tick();

      fixture.detectChanges();

      let buttonDebugElements: DebugElement[] = fixture.debugElement.queryAll(
        By.css('button')
      );
      expect(buttonDebugElements.length).toEqual(4);
      expect(buttonDebugElements[1].nativeElement.innerText).toEqual(
        'Take Vacation'
      );

      // Open work days modal
      buttonDebugElements[1].triggerEventHandler('click', mockEmployeesData[0]);
      fixture.detectChanges();
    }));
  });
});
