import { Component, Inject, OnInit } from '@angular/core';

import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import WorkDays from '../../models/WorkDays';

@Component({
  selector: 'app-take-vacation',
  templateUrl: './take-vacation.component.html',
  styleUrls: ['./take-vacation.component.css'],
})
export class TakeVacationComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<TakeVacationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: WorkDays
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {}
}
