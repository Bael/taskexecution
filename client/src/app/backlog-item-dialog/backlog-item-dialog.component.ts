import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {BacklogItem} from '../backlog-item';

@Component({
  selector: 'app-backlog-item-dialog',
  templateUrl: './backlog-item-dialog.component.html',
  styleUrls: ['./backlog-item-dialog.component.css']
})
export class BacklogItemDialogComponent implements OnInit {


  constructor(public dialogRef: MatDialogRef<BacklogItemDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: BacklogItem) {
  }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


}
