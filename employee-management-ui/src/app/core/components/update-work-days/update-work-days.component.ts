import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import Employee from '../../models/Employee';
import WorkDays from '../../models/WorkDays';

@Component({
  selector: 'app-update-work-days',
  templateUrl: './update-work-days.component.html',
  styleUrls: ['./update-work-days.component.css'],
})
export class UpdateWorkDaysComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<UpdateWorkDaysComponent>,
    @Inject(MAT_DIALOG_DATA) public data: WorkDays
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {}
}
