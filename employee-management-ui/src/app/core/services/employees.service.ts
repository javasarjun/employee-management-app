import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import Employee from '../models/Employee';
import WorkDays from '../models/WorkDays';

@Injectable({
  providedIn: 'root',
})
export class EmployeesService {
  uri: any;
  constructor(private http: HttpClient) {}

  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(
      `${environment.employeeManagementAPIUrl}/employees`
    );
  }

  setWorkDays(input: WorkDays): Observable<Employee> {
    return this.http.put<Employee>(
      `${environment.employeeManagementAPIUrl}/employee/${input.id}/days-worked/${input.daysWorked}`,
      {}
    );
  }

  takeVacation(input: WorkDays): Observable<Employee> {
    return this.http.put<Employee>(
      `${environment.employeeManagementAPIUrl}/employee/${input.id}/take-vacation/${input.vacationDays}`,
      {}
    );
  }
}
