import { TakeVacationComponent } from './../take-vacation/take-vacation.component';
import { UpdateWorkDaysComponent } from './../update-work-days/update-work-days.component';
import { EmployeesService } from './../../services/employees.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import Employee from '../../models/Employee';
import { MatDialog } from '@angular/material/dialog';
import WorkDays from '../../models/WorkDays';
import { Router } from '@angular/router';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MessageComponent } from '../message/message.component';
import { HttpErrorResponse } from '@angular/common/http';
import Response from '../../models/Response';

@Component({
  selector: 'app-employee-get',
  templateUrl: './employee-get.component.html',
  styleUrls: ['./employee-get.component.css'],
})
export class EmployeeGetComponent implements OnInit {
  displayedColumns: string[] = [
    'name',
    'type',
    'vacationDaysAccumulated',
    'daysWorked',
    'actions',
  ];

  dataSource: MatTableDataSource<Employee>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  employees: Employee[];

  constructor(
    private employeesService: EmployeesService,
    public dialog: MatDialog,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.employeesService.getEmployees().subscribe((data: Employee[]) => {
      this.employees = data;
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  openUpdateWorkDaysModal(employee: Employee): void {
    const modalInput = new WorkDays();
    modalInput.id = employee.id;
    modalInput.name = employee.name;
    const updateWorkDaysModalRef = this.dialog.open(UpdateWorkDaysComponent, {
      width: '350px',
      data: modalInput,
      disableClose: true,
      position: { top: '100' + 'px' },
    });

    updateWorkDaysModalRef.afterClosed().subscribe((input: WorkDays) => {
      if (input) {
        this.employeesService.setWorkDays(input).subscribe(
          (res: Employee) => {
            this.openResponseMessageModal(
              'Work Days Updated successfully for ' + res.name
            );
          },
          (err: HttpErrorResponse) => {
            this.openResponseMessageModal(err.error.message);
          }
        );

        this.reloadComponent();
      }
    });
  }

  openTakeVacationModal(employee: Employee): void {
    const modalInput = new WorkDays();
    modalInput.id = employee.id;
    modalInput.name = employee.name;
    const takeVacationModalRef = this.dialog.open(TakeVacationComponent, {
      width: '350px',
      data: modalInput,
      disableClose: true,
      position: { top: '100' + 'px' },
    });

    takeVacationModalRef.afterClosed().subscribe((input: WorkDays) => {
      if (input) {
        this.employeesService.takeVacation(input).subscribe(
          (res: Employee) => {
            this.openResponseMessageModal(
              'Vacation Days Updated successfully for ' + res.name
            );
          },
          (err: HttpErrorResponse) => {
            this.openResponseMessageModal(err.error.message);
          }
        );
        this.reloadComponent();
      }
    });
  }

  openResponseMessageModal(status: string): void {
    this.dialog.open(MessageComponent, {
      width: '400px',
      data: { message: status },
      disableClose: true,
      position: { top: '100' + 'px' },
    });
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  reloadComponent(): void {
    const currentUrl = this.router.url;
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate([currentUrl]);
  }
}
