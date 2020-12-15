import {
  async,
  fakeAsync,
  getTestBed,
  inject,
  TestBed,
  waitForAsync,
} from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { EmployeesService } from './employees.service';
import Employee from '../models/Employee';
import { environment } from 'src/environments/environment';
import WorkDays from '../models/WorkDays';

describe('EmployeesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EmployeesService],
      imports: [HttpClientTestingModule],
    });
  });

  describe(':', () => {
    function setup() {
      const employeeService = TestBed.inject(EmployeesService);
      const httpTestingController = TestBed.inject(HttpTestingController);
      return { employeeService, httpTestingController };
    }

    it('getAllEmployees', () => {
      const { employeeService, httpTestingController } = setup();
      const mockEmployeesData: Employee[] = [
        {
          id: '1',
          name: 'United states of america',
          daysWorked: 260,
          maxVacationDaysInWorkYear: 10,
          type: 'HOURLY',
          vacationDaysAccumulated: 10,
        },
      ];

      employeeService.getEmployees().subscribe((data) => {
        expect(data).toEqual(mockEmployeesData);
      });

      const req = httpTestingController.expectOne(
        `${environment.employeeManagementAPIUrl}/employees`
      );

      expect(req.request.method).toBe('GET');

      req.flush(mockEmployeesData);
    });

    it('setWorkDays', () => {
      const { employeeService, httpTestingController } = setup();

      const request: WorkDays = {
        id: '1',
        daysWorked: 260,
        name: 'HE',
        vacationDays: 0,
      };

      const mockEmployeeData: Employee = {
        id: '1',
        name: 'United states of america',
        daysWorked: 260,
        maxVacationDaysInWorkYear: 10,
        type: 'HOURLY',
        vacationDaysAccumulated: 10,
      };

      employeeService.setWorkDays(request).subscribe((data) => {
        expect(data).toEqual(mockEmployeeData);
      });

      const req = httpTestingController.expectOne(
        `${environment.employeeManagementAPIUrl}/employee/1/days-worked/260`
      );

      expect(req.request.method).toBe('PUT');

      req.flush(mockEmployeeData);
    });

    it('takeVacation when days greater than work year', () => {
      const { employeeService, httpTestingController } = setup();

      const request: WorkDays = {
        id: '1',
        daysWorked: 0,
        name: 'HE',
        vacationDays: 5,
      };

      const mockEmployeeData: Employee = {
        id: '1',
        name: 'United states of america',
        daysWorked: 260,
        maxVacationDaysInWorkYear: 10,
        type: 'HOURLY',
        vacationDaysAccumulated: 5,
      };

      employeeService.takeVacation(request).subscribe((data) => {
        expect(data).toEqual(mockEmployeeData);
      });

      const req = httpTestingController.expectOne(
        `${environment.employeeManagementAPIUrl}/employee/1/take-vacation/5`
      );

      expect(req.request.method).toBe('PUT');

      req.flush(mockEmployeeData);
    });

    afterEach(() => {
      const { httpTestingController } = setup();
      httpTestingController.verify();
    });
  });
});
